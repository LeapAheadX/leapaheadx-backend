package com.oop.leap_ahead_x.repos;


import com.oop.leap_ahead_x.domain.Approver;

import java.util.Optional;
import java.util.UUID;

import com.oop.leap_ahead_x.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproverRepository extends JpaRepository<Approver, UUID> {
    Approver findByuId(Optional<User> uid);
}
