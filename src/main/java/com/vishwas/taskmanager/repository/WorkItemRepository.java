package com.vishwas.taskmanager.repository;


import com.vishwas.taskmanager.entity.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {

}
