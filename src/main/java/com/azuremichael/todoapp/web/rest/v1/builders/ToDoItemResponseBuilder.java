package com.azuremichael.todoapp.web.rest.v1.builders;

import com.azuremichael.todoapp.models.ToDoItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoItemResponseBuilder {

    public ResponseEntity<ToDoItem> ok(ToDoItem toDoItem) {
        return ResponseEntity.ok(toDoItem);
    }

    public ResponseEntity<List<ToDoItem>> ok(List<ToDoItem> toDoItems) {
        return ResponseEntity.ok(toDoItems);
    }

    public ResponseEntity<Void> deleted() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ToDoItem> save(ToDoItem toDoItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoItem);
    }
 }
