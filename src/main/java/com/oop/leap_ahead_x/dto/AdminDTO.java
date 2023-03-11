package com.oop.leap_ahead_x.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;


public class AdminDTO {

    private UUID adminUuid;

    @Size(max = 255)
    private String department;

    @Size(max = 255)
    private String country;

    @NotNull
    @JsonProperty("uId")
    private UUID uId;

    public UUID getAdminUuid() {
        return adminUuid;
    }

    public void setAdminUuid(final UUID adminUuid) {
        this.adminUuid = adminUuid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(final String department) {
        this.department = department;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public UUID getUId() {
        return uId;
    }

    public void setUId(final UUID uId) {
        this.uId = uId;
    }

}
