package com.vishwas.taskmanager.mapper;

import com.vishwas.taskmanager.dto.CreateWorkItemRequest;
import com.vishwas.taskmanager.dto.UpdateWorkItemRequest;
import com.vishwas.taskmanager.dto.WorkItemResponse;
import com.vishwas.taskmanager.entity.User;
import com.vishwas.taskmanager.entity.WorkItem;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class WorkItemMapper {

    public static WorkItem toEntity(CreateWorkItemRequest request){
        WorkItem workItem = new WorkItem();


        workItem.setTitle(request.title());
        workItem.setDescription(request.description());
        workItem.setType(request.type());
        workItem.setStatus(request.status());
        workItem.setPriority(request.priority());
        workItem.setAssignedTo(request.assignedTo());


        return workItem;
    }

    public static WorkItemResponse toResponse(WorkItem workItem){

        return new WorkItemResponse(
                workItem.getId(),
                workItem.getTitle(),
                workItem.getDescription(),
                workItem.getType(),
                workItem.getStatus(),
                workItem.getPriority(),
                workItem.getAssignedTo(),
                workItem.getCreatedBy()
        );
    }

    public static WorkItem updateEntity(WorkItem workItem, UpdateWorkItemRequest request){

        workItem.setTitle(request.title());
        workItem.setDescription(request.description());
        workItem.setPriority(request.priority());
        workItem.setStatus(request.status());
        workItem.setAssignedTo(request.assignedTo());

        return workItem;
    }
}
