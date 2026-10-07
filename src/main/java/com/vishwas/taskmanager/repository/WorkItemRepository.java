package com.vishwas.taskmanager.repository;


import com.vishwas.taskmanager.entity.WorkItem;
import com.vishwas.taskmanager.entity.WorkItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkItemRepository
        extends JpaRepository<WorkItem, Long>,
                JpaSpecificationExecutor<WorkItem> {
    Page<WorkItem> findByStatus(WorkItemStatus status, Pageable pageable);
}
