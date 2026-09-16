package com.vishwas.taskmanager.dto;

import com.vishwas.taskmanager.entity.WorkItemPriority;
import com.vishwas.taskmanager.entity.WorkItemStatus;
import com.vishwas.taskmanager.entity.WorkItemType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CreateWorkItemRequest(

        @NotNull(message = "Title cannot be empty")
        String title,

        String description,

        WorkItemType type,

        WorkItemStatus status,

        WorkItemPriority priority,

        Long assignedTo
) {
}
