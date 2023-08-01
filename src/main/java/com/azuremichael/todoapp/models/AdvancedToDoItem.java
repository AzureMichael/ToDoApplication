package com.azuremichael.todoapp.models;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdvancedToDoItem implements Serializable {

    @Id
    private Long id;
    
    private UUID uuid;
    private List<UUID> types;

    protected List<AdvancedToDoItem> children;
    protected String description;

    protected Boolean isComplete;
    
    private Instant createdAt;
    private Instant updatedAt;

}
