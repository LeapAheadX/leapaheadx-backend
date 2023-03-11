package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.FormWorkflow;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormWorkflowRepository extends JpaRepository<FormWorkflow, UUID> {

    boolean existsByNameIgnoreCase(String name);

}
