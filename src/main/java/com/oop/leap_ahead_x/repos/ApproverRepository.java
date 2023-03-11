package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Approver;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproverRepository extends JpaRepository<Approver, UUID> {
}
