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
public class SubformCanvas {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID canvasUuid;

    @Column(nullable = false)
    private String name;

    @Column(name = "\"description\"")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private Admin createdBy;

    @ManyToMany(mappedBy = "associatedSubformSubformCanvass")
    private Set<FormStep> associatedSubformFormSteps;

    @OneToMany(mappedBy = "parentCanvas")
    private Set<InputComponent> parentCanvasInputComponents;

    @OneToMany(mappedBy = "canvasUuid")
    private Set<ApplicationResponseValue> canvasUuidApplicationResponseValues;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public UUID getCanvasUuid() {
        return canvasUuid;
    }

    public void setCanvasUuid(final UUID canvasUuid) {
        this.canvasUuid = canvasUuid;
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

    public Set<FormStep> getAssociatedSubformFormSteps() {
        return associatedSubformFormSteps;
    }

    public void setAssociatedSubformFormSteps(final Set<FormStep> associatedSubformFormSteps) {
        this.associatedSubformFormSteps = associatedSubformFormSteps;
    }

    public Set<InputComponent> getParentCanvasInputComponents() {
        return parentCanvasInputComponents;
    }

    public void setParentCanvasInputComponents(
            final Set<InputComponent> parentCanvasInputComponents) {
        this.parentCanvasInputComponents = parentCanvasInputComponents;
    }

    public Set<ApplicationResponseValue> getCanvasUuidApplicationResponseValues() {
        return canvasUuidApplicationResponseValues;
    }

    public void setCanvasUuidApplicationResponseValues(
            final Set<ApplicationResponseValue> canvasUuidApplicationResponseValues) {
        this.canvasUuidApplicationResponseValues = canvasUuidApplicationResponseValues;
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
