package com.vishwas.taskmanager.service;

import com.vishwas.taskmanager.dto.CreateWorkItemRequest;
import com.vishwas.taskmanager.dto.UpdateWorkItemRequest;
import com.vishwas.taskmanager.dto.WorkItemResponse;
import com.vishwas.taskmanager.entity.*;
import com.vishwas.taskmanager.exception.ResourceNotFoundException;
import com.vishwas.taskmanager.exception.WorkItemAccessDeniedException;
import com.vishwas.taskmanager.exception.WorkItemVersionConflictException;
import com.vishwas.taskmanager.mapper.WorkItemMapper;
import com.vishwas.taskmanager.repository.UserRepository;
import com.vishwas.taskmanager.repository.WorkItemRepository;
import com.vishwas.taskmanager.specification.WorkItemSpecification;
import jakarta.persistence.OptimisticLockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class WorkItemService {


     private final WorkItemRepository workItemRepository;
     private final UserRepository userRepository;
     private final WorkItemWorkFlowService workItemWorkFlowService;

     public WorkItemService(WorkItemRepository workItemRepository, UserRepository userRepository, WorkItemWorkFlowService workItemWorkFlowService){
         this.workItemRepository = workItemRepository;
         this.userRepository = userRepository;
         this.workItemWorkFlowService = workItemWorkFlowService;
     }

    public WorkItemResponse createWorkItem(CreateWorkItemRequest request){

         WorkItem workItem = WorkItemMapper.toEntity(request);

         if(request.assignedTo()!=0){
             userRepository.findById(request.assignedTo())
                     .orElseThrow(()->
                             new ResourceNotFoundException(
                                     "Assigned User not found with ID:" + request.assignedTo()
                             ));
         }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->
                        new ResourceNotFoundException("User Not found with email" + email));


        workItem.setCreatedBy(user.getId());
        WorkItem savedWorkItem = workItemRepository.save(workItem);


         return WorkItemMapper.toResponse(savedWorkItem);


    }

    public Page<WorkItemResponse> getAllWorkItems(WorkItemStatus status, WorkItemPriority priority, WorkItemType type, Pageable pageable){

        Specification<WorkItem> specification = null;

        if(status!=null){
            specification = WorkItemSpecification.hasStatus(status);
        }

        if(priority!= null){
            Specification<WorkItem> prioritySpec = WorkItemSpecification.hasPriority(priority);
            if(specification==null){
                specification = prioritySpec;
            }
            else {
                specification = specification.and(prioritySpec);
            }
        }

        if(type!= null){
            Specification<WorkItem> typeSpec = WorkItemSpecification.hasType(type);
            if (specification==null){
                specification=typeSpec;
            }
            else {
                specification =specification.and(typeSpec);
            }
        }
         Page<WorkItem> workItems;

         if(specification!= null){
             workItems = workItemRepository.findAll(specification, pageable);
         }else {
             workItems = workItemRepository.findAll(pageable);
         }
         return workItems.map(WorkItemMapper::toResponse);

    }

    public WorkItemResponse getWorkItemById(Long id){

         WorkItem workItem = workItemRepository.findById(id)
                 .orElseThrow(()->
                         new ResourceNotFoundException("Work-Item not found with ID: "+ id));

         return WorkItemMapper.toResponse(workItem);
    }

    public WorkItemResponse updateWorkItem(Long id, UpdateWorkItemRequest request){

         WorkItem workItem = workItemRepository.findById(id)
                 .orElseThrow(()->
                         new ResourceNotFoundException("Work Item not found with ID: " + id));

         if(!workItem.getVersion().equals(request.version())){
             throw new WorkItemVersionConflictException("WorkItem was modified by another user");
         }

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

         if(!canModifyWorkItem(workItem, authentication)){
             throw new WorkItemAccessDeniedException("You are not allowed to perform this action");
         }

        if(request.assignedTo()!=0){
            userRepository.findById(request.assignedTo())
                    .orElseThrow(()->
                            new ResourceNotFoundException(
                                    "Assigned User not found with ID:" + request.assignedTo()
                            ));
        }
        workItemWorkFlowService.validateTransition(workItem.getStatus(), request.status());

        WorkItem updatedWorkItem = workItemRepository.save(WorkItemMapper.updateEntity(workItem, request));
        return WorkItemMapper.toResponse(updatedWorkItem);
    }

    public boolean canModifyWorkItem(WorkItem workItem, Authentication authentication){

         String userEmail = authentication.getName();
         User user = userRepository.findByEmail(userEmail)
                 .orElseThrow(()->
                         new ResourceNotFoundException("User not found with email" + userEmail));

         if(user.getRole() == Role.ADMIN){
             return true;
         }

         return (user.getId().equals(workItem.getAssignedTo()));
    }
}
