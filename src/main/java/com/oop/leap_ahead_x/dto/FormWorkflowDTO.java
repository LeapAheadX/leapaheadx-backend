package com.oop.leap_ahead_x.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.UUID;


public class FormWorkflowDTO {

    private UUID formUuid;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    private UUID createdBy;

    @NotNull
    private String dateCreated;

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    private boolean archive;

    public FormWorkflowDTO() {
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UUID getFormUuid() {
        return formUuid;
    }

    public void setFormUuid(final UUID formUuid) {
        this.formUuid = formUuid;
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

}
