package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
