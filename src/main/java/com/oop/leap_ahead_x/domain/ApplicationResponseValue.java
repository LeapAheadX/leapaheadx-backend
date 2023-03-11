package com.oop.leap_ahead_x.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class ApplicationResponseValue {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer responseId;

    @Column(nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_uuid", nullable = false)
    private Application applicationUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canvas_uuid", nullable = false)
    private SubformCanvas canvasUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_uuid", nullable = false)
    private InputComponent componentUuid;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(final Integer responseId) {
        this.responseId = responseId;
    }

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
