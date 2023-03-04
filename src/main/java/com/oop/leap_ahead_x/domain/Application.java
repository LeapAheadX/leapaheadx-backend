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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_uuid", nullable = false)
    private Form formUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitted_by", nullable = false)
    private User submittedBy;

    @OneToMany(mappedBy = "applicationUuid")
    private Set<ApplicationResponseValue> applicationUuidApplicationResponseValues;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Form getFormUuid() {
        return formUuid;
    }

    public void setFormUuid(final Form formUuid) {
        this.formUuid = formUuid;
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(final User submittedBy) {
        this.submittedBy = submittedBy;
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

}
