package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.FormWorkflow;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface FormWorkflowRepository extends JpaRepository<FormWorkflow, UUID> {

    boolean existsByNameIgnoreCase(String name);

    @Async
    @Query("SELECT formWorkflow from FormWorkflow formWorkflow where formWorkflow.name= :name")
    FormWorkflow findByFormName(String name);

}
