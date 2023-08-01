package com.azuremichael.todoapp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_items")
public class ToDoItem implements Serializable {

    @Id
    private Long id;

    protected String description;

    protected Boolean isComplete;

    private Instant createdAt;

    private Instant updatedAt;

    protected List<ToDoItem> children;

}
