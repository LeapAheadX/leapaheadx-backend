package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, UUID> {
}
