package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.FormStep;

import java.util.List;
import java.util.UUID;

import com.oop.leap_ahead_x.domain.FormWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormStepRepository extends JpaRepository<FormStep, UUID> {
    List<FormStep> findByParentForm(FormWorkflow form);
    FormStep findByParentFormAndAction(FormWorkflow form,String action);

    FormStep findByStepUuid(UUID formstep);
    FormStep findByParentFormAndOrderNo(FormWorkflow form, int orderNo);
}
