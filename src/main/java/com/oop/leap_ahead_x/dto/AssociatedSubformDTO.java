package com.oop.leap_ahead_x.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;


public class AssociatedSubformDTO {

    private Integer position;

    @NotNull
    private UUID stepUuid;

    @NotNull
    private UUID canvasUuid;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(final Integer position) {
        this.position = position;
    }

    public UUID getStepUuid() {
        return stepUuid;
    }

    public void setStepUuid(final UUID stepUuid) {
        this.stepUuid = stepUuid;
    }

    public UUID getCanvasUuid() {
        return canvasUuid;
    }

    public void setCanvasUuid(final UUID canvasUuid) {
        this.canvasUuid = canvasUuid;
    }

}
