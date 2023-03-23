package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Admin;

import java.util.Optional;
import java.util.UUID;

import com.oop.leap_ahead_x.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Admin findByuId(Optional<User> uid);

}
