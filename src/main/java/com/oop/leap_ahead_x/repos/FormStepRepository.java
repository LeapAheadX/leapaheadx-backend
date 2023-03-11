package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.FormStep;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormStepRepository extends JpaRepository<FormStep, UUID> {
}
