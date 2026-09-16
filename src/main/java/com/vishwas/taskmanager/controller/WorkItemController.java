package com.vishwas.taskmanager.controller;


import com.vishwas.taskmanager.dto.CreateWorkItemRequest;
import com.vishwas.taskmanager.dto.UpdateWorkItemRequest;
import com.vishwas.taskmanager.dto.WorkItemResponse;
import com.vishwas.taskmanager.service.WorkItemService;
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
    public List<WorkItemResponse> getAllWorkItems(){
        return workItemService.getAllWorkItems();
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
