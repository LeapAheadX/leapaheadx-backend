package com.oop.leap_ahead_x.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(UniqueApplicationResponseID.class)
public class ApplicationResponseValue {


    @Column(nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_uuid", nullable = false)
    @Id
    private Application applicationUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canvas_uuid", nullable = false)
    @Id
    private SubformCanvas canvasUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_uuid", nullable = false)
    @Id
    private InputComponent componentUuid;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;



    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public Application getApplicationUuid() {
        return applicationUuid;
    }

    public void setApplicationUuid(final Application applicationUuid) {
        this.applicationUuid = applicationUuid;
    }

    public SubformCanvas getCanvasUuid() {
        return canvasUuid;
    }

    public void setCanvasUuid(final SubformCanvas canvasUuid) {
        this.canvasUuid = canvasUuid;
    }

    public InputComponent getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(final InputComponent componentUuid) {
        this.componentUuid = componentUuid;
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