package com.vishwas.taskmanager.service;

import com.vishwas.taskmanager.entity.WorkItemStatus;
import com.vishwas.taskmanager.exception.InvalidWorkItemTransitionException;
import org.springframework.stereotype.Service;

@Service
public class WorkItemWorkFlowService {

    public void validateTransition(WorkItemStatus currentStatus, WorkItemStatus newStatus){
        if(currentStatus==newStatus) return;
        if(currentStatus==WorkItemStatus.PROPOSED && newStatus == WorkItemStatus.ACTIVE) return;
        if(currentStatus==WorkItemStatus.ACTIVE && newStatus == WorkItemStatus.RESOLVED) return;
        if(currentStatus==WorkItemStatus.RESOLVED && newStatus == WorkItemStatus.CLOSED) return;
        throw new InvalidWorkItemTransitionException("Invalid Status Transition From "+ currentStatus + " to " + newStatus);
    }
}
