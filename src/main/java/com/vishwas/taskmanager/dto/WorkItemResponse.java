package com.vishwas.taskmanager.dto;

import com.vishwas.taskmanager.entity.WorkItemPriority;
import com.vishwas.taskmanager.entity.WorkItemStatus;
import com.vishwas.taskmanager.entity.WorkItemType;

public record WorkItemResponse(

        Long id,
        String title,
        String description,
        WorkItemType type,
        WorkItemStatus status,
        WorkItemPriority priority,
        Long assignedTo,
        Long createdBy
) {
}
