package com.oop.leap_ahead_x.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;


public class ApplicationDTO {

    private UUID applicationUuid;

    @NotNull
    @Size(max = 50)
    private String status;

    @NotNull
    @Size(max = 500)
    private String comment;

    @NotNull
    @Size(max = 225)
    private String company;

    @NotNull
    @Size(max = 225)
    private String formName;



    @NotNull
    private Integer currentStepNo;

//    @NotNull
//    private UUID createdFor;

//    @NotNull
//    private UUID formUuid;



    public UUID getApplicationUuid() {
        return applicationUuid;
    }

    public void setApplicationUuid(final UUID applicationUuid) {
        this.applicationUuid = applicationUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public Integer getCurrentStepNo() {
        return currentStepNo;
    }

    public void setCurrentStepNo(final Integer currentStepNo) {
        this.currentStepNo = currentStepNo;
    }

//    public UUID getCreatedFor() {
//        return createdFor;
//    }
//
//    public void setCreatedFor(final UUID createdFor) {
//        this.createdFor = createdFor;
//    }

//    public UUID getFormUuid() {
//        return formUuid;
//    }
//
//    public void setFormUuid(final UUID formUuid) {
//        this.formUuid = formUuid;
//    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

}
