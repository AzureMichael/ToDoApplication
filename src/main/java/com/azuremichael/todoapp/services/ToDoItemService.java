package com.azuremichael.todoapp.services;

import com.azuremichael.todoapp.models.ToDoItem;
import com.azuremichael.todoapp.repositories.ToDoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoItemService {
    private final ToDoItemRepository toDoItemRepository;

    public Flux<ToDoItem> getAll() {
        return Flux.fromIterable(List.of(ToDoItem.builder().build()));
    }

    public Mono<ToDoItem> getById(Long id) {
        return Mono.just(ToDoItem.builder().id(id).build());
    }

    public Mono<ToDoItem> save(ToDoItem toDoItem) {
        if(toDoItem.getId() == null) {
            toDoItem.setCreatedAt(Instant.now());
        }

        toDoItem.setUpdatedAt(Instant.now());
        return Mono.just(toDoItem);
    }

    public Mono<Void> delete(ToDoItem toDoItem) {
        return Mono.empty();
    }
}
