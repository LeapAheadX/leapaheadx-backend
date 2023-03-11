package com.oop.leap_ahead_x.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;


public class ApproverDTO {

    private UUID approverUuid;

    private Integer approvalTier;

    @NotNull
    @JsonProperty("uId")
    private UUID uId;

    public UUID getApproverUuid() {
        return approverUuid;
    }

    public void setApproverUuid(final UUID approverUuid) {
        this.approverUuid = approverUuid;
    }

    public Integer getApprovalTier() {
        return approvalTier;
    }

    public void setApprovalTier(final Integer approvalTier) {
        this.approvalTier = approvalTier;
    }

    public UUID getUId() {
        return uId;
    }

    public void setUId(final UUID uId) {
        this.uId = uId;
    }

}
