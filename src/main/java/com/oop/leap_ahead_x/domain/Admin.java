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
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "\"admin\"")
@EntityListeners(AuditingEntityListener.class)
public class Admin {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID adminUuid;

    @Column
    private String department;

    @Column
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id", nullable = false)
    private User uId;

    @OneToMany(mappedBy = "createdBy")
    private Set<FormWorkflow> createdByFormWorkflows;

    @OneToMany(mappedBy = "createdBy")
    private Set<SubformCanvas> createdBySubformCanvass;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public User getUId() {
        return uId;
    }

    public void setUId(final User uId) {
        this.uId = uId;
    }

    public Set<FormWorkflow> getCreatedByFormWorkflows() {
        return createdByFormWorkflows;
    }

    public void setCreatedByFormWorkflows(final Set<FormWorkflow> createdByFormWorkflows) {
        this.createdByFormWorkflows = createdByFormWorkflows;
    }

    public Set<SubformCanvas> getCreatedBySubformCanvass() {
        return createdBySubformCanvass;
    }

    public void setCreatedBySubformCanvass(final Set<SubformCanvas> createdBySubformCanvass) {
        this.createdBySubformCanvass = createdBySubformCanvass;
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
