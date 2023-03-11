package com.oop.leap_ahead_x.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;


public class ApplicationResponseValueDTO {

    private Integer responseId;

    @NotNull
    @Size(max = 255)
    private String value;

    @NotNull
    private UUID applicationUuid;

    @NotNull
    private UUID canvasUuid;

    @NotNull
    private UUID componentUuid;

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(final Integer responseId) {
        this.responseId = responseId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public UUID getApplicationUuid() {
        return applicationUuid;
    }

    public void setApplicationUuid(final UUID applicationUuid) {
        this.applicationUuid = applicationUuid;
    }

    public UUID getCanvasUuid() {
        return canvasUuid;
    }

    public void setCanvasUuid(final UUID canvasUuid) {
        this.canvasUuid = canvasUuid;
    }

    public UUID getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(final UUID componentUuid) {
        this.componentUuid = componentUuid;
    }

}
