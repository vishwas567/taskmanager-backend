package com.vishwas.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "work_item")
public class WorkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private WorkItemType type;

    @Enumerated(EnumType.STRING)
    private WorkItemStatus status;

    @Enumerated(EnumType.STRING)
    private  WorkItemPriority priority;

    @Column(nullable = false)
    private Long assignedTo;

    @Column(nullable = false)
    private Long createdBy;


}
