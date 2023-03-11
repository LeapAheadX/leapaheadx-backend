package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Vendor;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    @Async
    @Query ("SELECT vendor from Vendor vendor where vendor.company= :company")
    Vendor findByCompanyName(String company);
}
