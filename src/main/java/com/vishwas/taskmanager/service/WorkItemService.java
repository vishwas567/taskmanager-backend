package com.vishwas.taskmanager.service;

import com.vishwas.taskmanager.dto.CreateWorkItemRequest;
import com.vishwas.taskmanager.dto.UpdateWorkItemRequest;
import com.vishwas.taskmanager.dto.WorkItemResponse;
import com.vishwas.taskmanager.entity.User;
import com.vishwas.taskmanager.entity.WorkItem;
import com.vishwas.taskmanager.exception.ResourceNotFoundException;
import com.vishwas.taskmanager.mapper.WorkItemMapper;
import com.vishwas.taskmanager.repository.UserRepository;
import com.vishwas.taskmanager.repository.WorkItemRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkItemService {


     private final WorkItemRepository workItemRepository;
     private final UserRepository userRepository;

     public WorkItemService(WorkItemRepository workItemRepository, UserRepository userRepository){
         this.workItemRepository = workItemRepository;
         this.userRepository = userRepository;
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

    public List<WorkItemResponse> getAllWorkItems(){
         return workItemRepository.findAll()
                 .stream()
                 .map(WorkItemMapper::toResponse)
                 .toList();

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

        if(request.assignedTo()!=0){
            userRepository.findById(request.assignedTo())
                    .orElseThrow(()->
                            new ResourceNotFoundException(
                                    "Assigned User not found with ID:" + request.assignedTo()
                            ));
        }

         WorkItem updatedWorkItem = workItemRepository.save(WorkItemMapper.updateEntity(workItem, request));
         return WorkItemMapper.toResponse(updatedWorkItem);
    }
}
