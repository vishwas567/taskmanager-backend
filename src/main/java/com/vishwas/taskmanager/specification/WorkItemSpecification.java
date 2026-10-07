package com.vishwas.taskmanager.specification;

import com.vishwas.taskmanager.entity.WorkItem;
import com.vishwas.taskmanager.entity.WorkItemPriority;
import com.vishwas.taskmanager.entity.WorkItemStatus;
import com.vishwas.taskmanager.entity.WorkItemType;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.data.jpa.domain.Specification;

public class WorkItemSpecification {
    public static Specification<WorkItem> hasStatus(WorkItemStatus status){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("status"),
                        status
                ));
    }

    public static Specification<WorkItem> hasPriority(WorkItemPriority priority){

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("priority"),
                        priority
                ));
    }

    public static Specification<WorkItem> hasType(WorkItemType type){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("type"),
                        type
                ));
    }
}
