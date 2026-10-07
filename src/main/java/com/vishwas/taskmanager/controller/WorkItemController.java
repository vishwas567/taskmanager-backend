package com.vishwas.taskmanager.controller;


import com.vishwas.taskmanager.dto.CreateWorkItemRequest;
import com.vishwas.taskmanager.dto.UpdateWorkItemRequest;
import com.vishwas.taskmanager.dto.WorkItemResponse;
import com.vishwas.taskmanager.entity.WorkItemPriority;
import com.vishwas.taskmanager.entity.WorkItemStatus;
import com.vishwas.taskmanager.entity.WorkItemType;
import com.vishwas.taskmanager.service.WorkItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-items")
public class WorkItemController {

    private final WorkItemService workItemService;

    public WorkItemController(WorkItemService workItemService) {
        this.workItemService = workItemService;
    }

    @PostMapping
    public WorkItemResponse createWorkItem(@RequestBody CreateWorkItemRequest request){

        return workItemService.createWorkItem(request);
    }

    @GetMapping
    public Page<WorkItemResponse> getAllWorkItems(@RequestParam(required = false) WorkItemStatus status,
                                                  @RequestParam(required = false) WorkItemPriority priority,
                                                  @RequestParam(required = false) WorkItemType type,
                                                  Pageable pageable){
        return workItemService.getAllWorkItems(status, priority, type, pageable);
    }

    @GetMapping("/{id}")
    public WorkItemResponse getWorkItemById(@PathVariable Long id){
        return workItemService.getWorkItemById(id);
    }

    @PutMapping("/{id}")
    public WorkItemResponse updateWorkItem(@PathVariable Long id, @RequestBody UpdateWorkItemRequest request){
        return workItemService.updateWorkItem(id, request);
    }
}
