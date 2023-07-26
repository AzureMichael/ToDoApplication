package com.azuremichael.todoapp.repositories;

import com.azuremichael.todoapp.models.ToDoItem;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends R2dbcRepository<ToDoItem, Long> {
}

