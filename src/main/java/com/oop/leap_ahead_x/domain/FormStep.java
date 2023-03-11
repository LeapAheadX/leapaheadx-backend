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
public class FormStep {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID stepUuid;

    @Column(nullable = false, length = 50)
    private String assigneeType;

    @Column(nullable = false)
    private Integer orderNo;

    @Column(nullable = false, length = 50)
    private String action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_form", nullable = false)
    private FormWorkflow parentForm;

    @ManyToMany
    @JoinTable(
            name = "associated_subform",
            joinColumns = @JoinColumn(name = "step_uuid"),
            inverseJoinColumns = @JoinColumn(name = "canvas_uuid")
    )
    private Set<SubformCanvas> associatedSubformSubformCanvass;

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

    public FormWorkflow getParentForm() {
        return parentForm;
    }

    public void setParentForm(final FormWorkflow parentForm) {
        this.parentForm = parentForm;
    }

    public Set<SubformCanvas> getAssociatedSubformSubformCanvass() {
        return associatedSubformSubformCanvass;
    }

    public void setAssociatedSubformSubformCanvass(
            final Set<SubformCanvas> associatedSubformSubformCanvass) {
        this.associatedSubformSubformCanvass = associatedSubformSubformCanvass;
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
