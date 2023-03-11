package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.ApplicationResponseValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationResponseValueRepository extends JpaRepository<ApplicationResponseValue, Integer> {
}
