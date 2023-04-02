package com.oop.leap_ahead_x.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Vendor {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID vendorUuid;

    @Column
    private String company;

    @Column
    private String country;

    @Column(unique=true)
    private String companyRegistrationNo;

    @Column
    private String businessNature;

    @Column(unique=true)
    private String gstNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "u_id", nullable = false, unique = true)
    private User uId;

    @OneToMany(mappedBy = "createdFor")
    private Set<Application> createdForApplications;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public String getCompanyRegistrationNo() {
        return companyRegistrationNo;
    }

    public void setCompanyRegistrationNo(String companyRegistrationNo) {
        this.companyRegistrationNo = companyRegistrationNo;
    }

    public String getBusinessNature() {
        return businessNature;
    }

    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public User getUId() {
        return uId;
    }

    public void setUId(final User uId) {
        this.uId = uId;
    }

    public Set<Application> getCreatedForApplications() {
        return createdForApplications;
    }

    public void setCreatedForApplications(final Set<Application> createdForApplications) {
        this.createdForApplications = createdForApplications;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
