package com.azuremichael.todoapp.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.azuremichael.todoapp.models.AdvancedToDoItem;

@Repository
public interface ToDoItemRepository extends R2dbcRepository<AdvancedToDoItem, Long> {
}

