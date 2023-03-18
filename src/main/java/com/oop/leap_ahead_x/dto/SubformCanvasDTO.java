package com.oop.leap_ahead_x.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.UUID;


public class SubformCanvasDTO {

    private UUID canvasUuid;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    private UUID createdBy;

    private OffsetDateTime dateCreated;

    public UUID getCanvasUuid() {
        return canvasUuid;
    }

    public void setCanvasUuid(final UUID canvasUuid) {
        this.canvasUuid = canvasUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final UUID createdBy) {
        this.createdBy = createdBy;
    }

    public OffsetDateTime getDateCreated() { return dateCreated; }

    public void setDateCreated(OffsetDateTime dateCreated) { this.dateCreated = dateCreated; }

}
