package com.oop.leap_ahead_x.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;


public class FormStepDTO {

    private UUID stepUuid;

    @NotNull
    @Size(max = 50)
    private String assigneeType;

    @NotNull
    private Integer orderNo;

    @NotNull
    @Size(max = 50)
    private String action;

    @NotNull
    private UUID parentForm;

    private List<UUID> associatedSubformSubformCanvass;

    public UUID getStepUuid() {
        return stepUuid;
    }

    public void setStepUuid(final UUID stepUuid) {
        this.stepUuid = stepUuid;
    }

    public String getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(final String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(final Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public UUID getParentForm() {
        return parentForm;
    }

    public void setParentForm(final UUID parentForm) {
        this.parentForm = parentForm;
    }

    public List<UUID> getAssociatedSubformSubformCanvass() {
        return associatedSubformSubformCanvass;
    }

    public void setAssociatedSubformSubformCanvass(
            final List<UUID> associatedSubformSubformCanvass) {
        this.associatedSubformSubformCanvass = associatedSubformSubformCanvass;
    }

}
