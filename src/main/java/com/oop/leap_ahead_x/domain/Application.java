package com.oop.leap_ahead_x.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Application {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID applicationUuid;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(nullable = true, length = 500)
    private String comment;

    @Column(nullable = false)
    private Integer currentStepNo;

    @Column(nullable = false)
    private OffsetDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_for", nullable = false)
    private Vendor createdFor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_uuid", nullable = false)
    private FormWorkflow formUuid;

    @OneToMany(mappedBy = "applicationUuid")
    private Set<ApplicationResponseValue> applicationUuidApplicationResponseValues;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @Column
    private boolean disabled;


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

    public Vendor getCreatedFor() {
        return createdFor;
    }

    public void setCreatedFor(final Vendor createdFor) {
        this.createdFor = createdFor;
    }

    public FormWorkflow getFormUuid() {
        return formUuid;
    }

    public void setFormUuid(final FormWorkflow formUuid) {
        this.formUuid = formUuid;
    }

    public Set<ApplicationResponseValue> getApplicationUuidApplicationResponseValues() {
        return applicationUuidApplicationResponseValues;
    }

    public void setApplicationUuidApplicationResponseValues(
            final Set<ApplicationResponseValue> applicationUuidApplicationResponseValues) {
        this.applicationUuidApplicationResponseValues = applicationUuidApplicationResponseValues;
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

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getDisabledStatus() {
        return disabled;
    }

    public void setDisabledStatus(final Boolean disabled) {
        this.disabled = disabled;
    }

}
