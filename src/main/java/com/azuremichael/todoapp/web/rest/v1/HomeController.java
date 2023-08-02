package com.azuremichael.todoapp.web.rest.v1;

import com.azuremichael.todoapp.models.AdvancedToDoItem;
import com.azuremichael.todoapp.services.ToDoItemService;
import com.azuremichael.todoapp.web.rest.v1.builders.ToDoItemResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final ToDoItemService toDoItemService;
    private final ToDoItemResponseBuilder toDoItemResponseBuilder;

    @GetMapping("/")
    public Mono<ResponseEntity<List<AdvancedToDoItem>>> getAll() {
        log.info("GET ALL CALLED");
        return toDoItemService.getAll()
                .collectList()
                .map(toDoItemResponseBuilder::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<AdvancedToDoItem>> getById(@PathVariable("id") Long id) {
        log.info("GET BY ID");
        return toDoItemService.getById(id)
                .map(toDo -> toDoItemResponseBuilder.ok(toDo));
    }

    @PostMapping("/")
    public Mono<ResponseEntity<AdvancedToDoItem>> save(@RequestBody Mono<AdvancedToDoItem> toDoItem) {
        log.info("POST TO SAVE TODO CALLED");
        return toDoItemService.save(toDoItem)
                .map(toDoItemResponseBuilder::ok);
    }

    @DeleteMapping("/")
    public Mono<ResponseEntity<Void>> delete(@RequestBody Mono<AdvancedToDoItem> toDoItem) {
        return toDoItemService.delete(toDoItem)
                .thenReturn(toDoItemResponseBuilder.deleted());
    }


    @PatchMapping(path = "/", consumes = "application/json-patch+json")
    public Mono<ResponseEntity<AdvancedToDoItem>> patch(@RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        AdvancedToDoItem toDoItem = AdvancedToDoItem.builder()
                .uuid(UUID.randomUUID())
                .types(List.of(UUID.randomUUID(), UUID.randomUUID()))
                .build();
        toDoItem.setChildren(List.of(AdvancedToDoItem.builder().build()));
        toDoItem.setDescription("Description");
        toDoItem.setIsComplete(false);

        return toDoItemService.updateToDoItem(jsonPatch)
        		.map(toDo -> ResponseEntity.ok(toDo));
    }

}
