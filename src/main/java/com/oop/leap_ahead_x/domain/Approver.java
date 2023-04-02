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
public class Approver {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private UUID approverUuid;

    @Column
    private Integer approvalTier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "u_id", nullable = false, unique = true)
    private User uId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public UUID getApproverUuid() {
        return approverUuid;
    }

    public void setApproverUuid(final UUID approverUuid) {
        this.approverUuid = approverUuid;
    }

    public Integer getApprovalTier() {
        return approvalTier;
    }

    public void setApprovalTier(final Integer approvalTier) {
        this.approvalTier = approvalTier;
    }

    public User getUId() {
        return uId;
    }

    public void setUId(final User uId) {
        this.uId = uId;
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
