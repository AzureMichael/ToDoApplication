package com.azuremichael.todoapp.web.rest.v1;

import com.azuremichael.todoapp.models.ToDoItem;
import com.azuremichael.todoapp.services.ToDoItemService;
import com.azuremichael.todoapp.web.rest.v1.builders.ToDoItemResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final ToDoItemService toDoItemService;
    private final ToDoItemResponseBuilder toDoItemResponseBuilder;

    @GetMapping("/")
    public Mono<ResponseEntity<List<ToDoItem>>> getAll() {
        log.info("GET ALL CALLED");
        return toDoItemService.getAll()
                .collectList()
                .map(toDoItemResponseBuilder::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ToDoItem>> getById(@PathVariable("id") Long id) {
        log.info("GET BY ID");
        return toDoItemService.getById(id)
                .map(toDoItemResponseBuilder::ok);
    }

    @PostMapping("/")
    public Mono<ResponseEntity<ToDoItem>> save(@RequestBody ToDoItem toDoItem) {
        log.info("POST TO SAVE TODO CALLED");
        return toDoItemService.save(toDoItem)
                .map(toDoItemResponseBuilder::ok);
    }

    @DeleteMapping("/")
    public Mono<ResponseEntity<Void>> delete(@RequestBody ToDoItem toDoItem) {
        return toDoItemService.delete(toDoItem)
                .thenReturn(toDoItemResponseBuilder.deleted());
    }
}
