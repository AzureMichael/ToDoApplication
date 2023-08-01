package com.azuremichael.todoapp.models;

import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdvancedToDoItem implements Serializable {

    private UUID uuid;
    private List<UUID> types;

    protected List<AdvancedToDoItem> children;
    protected String description;

    protected Boolean isComplete;

}
