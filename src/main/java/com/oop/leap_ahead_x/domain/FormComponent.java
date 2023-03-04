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
public class FormComponent {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID componentUuid;

    @Column(nullable = false)
    private String question;

    @Column(length = 50)
    private String type;

    @Column(nullable = false)
    private Boolean isRequired;

    @Column(nullable = false)
    private Integer orderNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_form", nullable = false)
    private Form parentForm;

    @ManyToMany
    @JoinTable(
            name = "option_component_link",
            joinColumns = @JoinColumn(name = "component_uuid"),
            inverseJoinColumns = @JoinColumn(name = "option_uuid")
    )
    private Set<Options> optionComponentLinkOptionss;

    @OneToMany(mappedBy = "componentUuid")
    private Set<ApplicationResponseValue> componentUuidApplicationResponseValues;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public UUID getComponentUuid() {
        return componentUuid;
    }

    public void setComponentUuid(final UUID componentUuid) {
        this.componentUuid = componentUuid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(final Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(final Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Form getParentForm() {
        return parentForm;
    }

    public void setParentForm(final Form parentForm) {
        this.parentForm = parentForm;
    }

    public Set<Options> getOptionComponentLinkOptionss() {
        return optionComponentLinkOptionss;
    }

    public void setOptionComponentLinkOptionss(final Set<Options> optionComponentLinkOptionss) {
        this.optionComponentLinkOptionss = optionComponentLinkOptionss;
    }

    public Set<ApplicationResponseValue> getComponentUuidApplicationResponseValues() {
        return componentUuidApplicationResponseValues;
    }

    public void setComponentUuidApplicationResponseValues(
            final Set<ApplicationResponseValue> componentUuidApplicationResponseValues) {
        this.componentUuidApplicationResponseValues = componentUuidApplicationResponseValues;
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
