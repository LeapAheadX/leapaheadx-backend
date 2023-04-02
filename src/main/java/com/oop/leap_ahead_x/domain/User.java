package com.oop.leap_ahead_x.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID uId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, name = "\"role\"", length = 50)
    private String role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean isDisabled;

    @OneToOne(mappedBy = "uId", fetch = FetchType.EAGER)
    private Admin uIdAdmin;

    @OneToOne(mappedBy = "uId", fetch = FetchType.EAGER)
    private Approver uIdApprover;

    @OneToOne(mappedBy = "uId", fetch = FetchType.EAGER)
    private Vendor uIdVendor;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public UUID getUId() {
        return uId;
    }

    public void setUId(final UUID uId) {
        this.uId = uId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public Admin getUIdAdmin() {
        return uIdAdmin;
    }

    public void setUIdAdmin(final Admin uIdAdmin) {
        this.uIdAdmin = uIdAdmin;
    }

    public Approver getUIdApprover() {
        return uIdApprover;
    }

    public void setUIdApprover(final Approver uIdApprover) {
        this.uIdApprover = uIdApprover;
    }

    public Vendor getUIdVendor() {
        return uIdVendor;
    }

    public void setUIdVendor(final Vendor uIdVendor) {
        this.uIdVendor = uIdVendor;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
