package com.oop.leap_ahead_x.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;


public class InputComponentDTO {

    private UUID componentUuid;

    @NotNull
    @Size(max = 255)
    private String question;

    @NotNull
    @Size(max = 50)
    private String type;

    @NotNull
    @JsonProperty("isRequired")
    private Boolean isRequired;

    @NotNull
    private Integer orderNo;

    @NotNull
    private UUID parentCanvas;

    public UUID getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(final UUID componentUuid) {
        this.componentUuid = componentUuid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(final Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(final Integer orderNo) {
        this.orderNo = orderNo;
    }

    public UUID getParentCanvas() {
        return parentCanvas;
    }

    public void setParentCanvas(final UUID parentCanvas) {
        this.parentCanvas = parentCanvas;
    }

}
