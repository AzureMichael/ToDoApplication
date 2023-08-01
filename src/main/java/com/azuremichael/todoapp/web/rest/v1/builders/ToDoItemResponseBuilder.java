package com.azuremichael.todoapp.web.rest.v1.builders;

import com.azuremichael.todoapp.models.AdvancedToDoItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoItemResponseBuilder {

    public ResponseEntity<AdvancedToDoItem> ok(AdvancedToDoItem toDo) {
        return ResponseEntity.ok(toDo);
    }

    public ResponseEntity<List<AdvancedToDoItem>> ok(List<AdvancedToDoItem> toDoItems) {
        return ResponseEntity.ok(toDoItems);
    }

    public ResponseEntity<Void> deleted() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<AdvancedToDoItem> save(AdvancedToDoItem toDoItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoItem);
    }
 }
