package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Application;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicationRepository extends JpaRepository<Application, UUID> {
}
