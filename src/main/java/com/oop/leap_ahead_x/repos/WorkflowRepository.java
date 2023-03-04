package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Workflow;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkflowRepository extends JpaRepository<Workflow, UUID> {
}
