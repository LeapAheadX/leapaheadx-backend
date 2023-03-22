package com.oop.leap_ahead_x.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class User {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID uId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, name = "\"role\"", length = 50)
    private String role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "uId")
    private Set<Admin> uIdAdmins;

    @OneToMany(mappedBy = "uId")
    private Set<Approver> uIdApprovers;

    @OneToMany(mappedBy = "uId")
    private Set<Vendor> uIdVendors;

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

    public Set<Admin> getUIdAdmins() {
        return uIdAdmins;
    }

    public void setUIdAdmins(final Set<Admin> uIdAdmins) {
        this.uIdAdmins = uIdAdmins;
    }

    public Set<Approver> getUIdApprovers() {
        return uIdApprovers;
    }

    public void setUIdApprovers(final Set<Approver> uIdApprovers) {
        this.uIdApprovers = uIdApprovers;
    }

    public Set<Vendor> getUIdVendors() {
        return uIdVendors;
    }

    public void setUIdVendors(final Set<Vendor> uIdVendors) {
        this.uIdVendors = uIdVendors;
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
