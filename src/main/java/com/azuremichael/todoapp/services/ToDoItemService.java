package com.azuremichael.todoapp.services;

import com.azuremichael.todoapp.models.AdvancedToDoItem;
import com.azuremichael.todoapp.repositories.ToDoItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoItemService {
    private final ToDoItemRepository toDoItemRepository;

    public Flux<AdvancedToDoItem> getAll() {
        return Flux.fromIterable(List.of(AdvancedToDoItem.builder().build()));
    }

    public Mono<AdvancedToDoItem> getById(Long id) {
        AdvancedToDoItem toDoItem = AdvancedToDoItem.builder()
                .uuid(UUID.randomUUID())
                .types(List.of(UUID.randomUUID(), UUID.randomUUID()))
                .build();
        
        toDoItem.setChildren(List.of(AdvancedToDoItem.builder().build()));
        toDoItem.setDescription("Description");
        toDoItem.setIsComplete(false);
        
        return Mono.just(toDoItem);
    }

    public Mono<AdvancedToDoItem> save(Mono<AdvancedToDoItem> toDoItem) {
       
        return toDoItem.map(toDo -> { 
        	if(toDo.getId() == null) {
        		toDo.setCreatedAt(Instant.now());
        	}
        	
        	toDo.setUpdatedAt(Instant.now());
        	
        	return toDo;
        });
    }

    public Mono<Void> delete(Mono<AdvancedToDoItem> toDoItem) {
        return Mono.empty();
    }
    
    public Mono<AdvancedToDoItem> updateToDoItem(JsonPatch patch) {
    	return getById(1L)
    			.map(toDo -> {
					try {
						return this.applyPatchToToDoItem(patch, toDo);
					} catch (JsonProcessingException | JsonPatchException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return toDo;
				});
    }
    
    private AdvancedToDoItem applyPatchToToDoItem(
            JsonPatch patch, AdvancedToDoItem toDoItem) throws JsonPatchException, JsonProcessingException {
        var objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(toDoItem, JsonNode.class));
        return objectMapper.treeToValue(patched, AdvancedToDoItem.class);
    }

}
