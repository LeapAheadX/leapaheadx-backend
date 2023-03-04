package com.oop.leap_ahead_x.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id")
    private User uId;

    @ManyToMany
    @JoinTable(
            name = "vendor_workflow_assignment",
            joinColumns = @JoinColumn(name = "vendor_uuid"),
            inverseJoinColumns = @JoinColumn(name = "workflow_uuid")
    )
    private Set<Workflow> vendorWorkflowAssignmentWorkflows;

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

    public User getUId() {
        return uId;
    }

    public void setUId(final User uId) {
        this.uId = uId;
    }

    public Set<Workflow> getVendorWorkflowAssignmentWorkflows() {
        return vendorWorkflowAssignmentWorkflows;
    }

    public void setVendorWorkflowAssignmentWorkflows(
            final Set<Workflow> vendorWorkflowAssignmentWorkflows) {
        this.vendorWorkflowAssignmentWorkflows = vendorWorkflowAssignmentWorkflows;
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
