package com.oop.leap_ahead_x.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;


public class VendorDTO {

    private UUID vendorUuid;

    @Size(max = 255)
    private String company;

    @Size(max = 255)
    private String country;

    @NotNull
    @JsonProperty("uId")
    private UUID uId;

    public UUID getVendorUuid() {
        return vendorUuid;
    }

    public void setVendorUuid(final UUID vendorUuid) {
        this.vendorUuid = vendorUuid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String company) {
        this.company = company;
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
