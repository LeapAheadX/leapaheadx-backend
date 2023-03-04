package com.oop.leap_ahead_x.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class WorkflowSteps {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID stepUuid;

    @Column(nullable = false, columnDefinition = "char(36)")
    private UUID associatedForm;

    @Column(nullable = false)
    private String name;

    @Column(name = "\"description\"")
    private String description;

    @Column(nullable = false)
    private Integer orderNo;

    @Column(nullable = false, length = 100)
    private String action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_workflow", nullable = false)
    private Workflow parentWorkflow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee", nullable = false)
    private User assignee;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public UUID getStepUuid() {
        return stepUuid;
    }

    public void setStepUuid(final UUID stepUuid) {
        this.stepUuid = stepUuid;
    }

    public UUID getAssociatedForm() {
        return associatedForm;
    }

    public void setAssociatedForm(final UUID associatedForm) {
        this.associatedForm = associatedForm;
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

    public Workflow getParentWorkflow() {
        return parentWorkflow;
    }

    public void setParentWorkflow(final Workflow parentWorkflow) {
        this.parentWorkflow = parentWorkflow;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(final User assignee) {
        this.assignee = assignee;
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
