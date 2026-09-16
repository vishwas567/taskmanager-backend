package com.vishwas.taskmanager.dto;

import com.vishwas.taskmanager.entity.WorkItemPriority;
import com.vishwas.taskmanager.entity.WorkItemStatus;
import com.vishwas.taskmanager.entity.WorkItemType;

public record UpdateWorkItemRequest(
        String title,
        String description,
        WorkItemStatus status,
        WorkItemPriority priority,
        Long assignedTo
) {
}
