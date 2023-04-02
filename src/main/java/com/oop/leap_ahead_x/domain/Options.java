package com.oop.leap_ahead_x.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Options {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID optionUuid;

    @Column(nullable = false)
    private String optionPrompt;

    @Column(nullable = false)
    private UUID parentInputComponent;

    @ManyToMany
    @JoinTable(
            name = "option_component_link",
            joinColumns = @JoinColumn(name = "option_uuid"),
            inverseJoinColumns = @JoinColumn(name = "component_uuid")
    )
    private Set<InputComponent> optionComponentLinkInputComponents;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public UUID getOptionUuid() {
        return optionUuid;
    }

    public void setOptionUuid(final UUID optionUuid) {
        this.optionUuid = optionUuid;
    }

    public String getOptionPrompt() {
        return optionPrompt;
    }

    public void setOptionPrompt(final String optionPrompt) {
        this.optionPrompt = optionPrompt;
    }

    public Set<InputComponent> getOptionComponentLinkInputComponents() {
        return optionComponentLinkInputComponents;
    }

    public void setOptionComponentLinkInputComponents(
            final Set<InputComponent> optionComponentLinkInputComponents) {
        this.optionComponentLinkInputComponents = optionComponentLinkInputComponents;
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

    public UUID getParentInputComponent() {
        return parentInputComponent;
    }

    public void setParentInputComponent(UUID parentInputComponent) {
        this.parentInputComponent = parentInputComponent;
    }

}
