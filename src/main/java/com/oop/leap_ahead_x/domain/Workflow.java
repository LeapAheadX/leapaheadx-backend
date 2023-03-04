package com.oop.leap_ahead_x.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Workflow {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID workflowUuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "\"description\"")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private Admin createdBy;

    @ManyToMany(mappedBy = "vendorWorkflowAssignmentWorkflows")
    private Set<Vendor> vendorWorkflowAssignmentVendors;

    @OneToMany(mappedBy = "parentWorkflow")
    private Set<WorkflowSteps> parentWorkflowWorkflowStepss;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public UUID getWorkflowUuid() {
        return workflowUuid;
    }

    public void setWorkflowUuid(final UUID workflowUuid) {
        this.workflowUuid = workflowUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Admin getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final Admin createdBy) {
        this.createdBy = createdBy;
    }

    public Set<Vendor> getVendorWorkflowAssignmentVendors() {
        return vendorWorkflowAssignmentVendors;
    }

    public void setVendorWorkflowAssignmentVendors(
            final Set<Vendor> vendorWorkflowAssignmentVendors) {
        this.vendorWorkflowAssignmentVendors = vendorWorkflowAssignmentVendors;
    }

    public Set<WorkflowSteps> getParentWorkflowWorkflowStepss() {
        return parentWorkflowWorkflowStepss;
    }

    public void setParentWorkflowWorkflowStepss(
            final Set<WorkflowSteps> parentWorkflowWorkflowStepss) {
        this.parentWorkflowWorkflowStepss = parentWorkflowWorkflowStepss;
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
