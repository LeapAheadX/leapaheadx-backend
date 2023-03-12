package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.AssociatedSubform;
import com.oop.leap_ahead_x.domain.FormStep;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;



public interface AssociatedSubformRepository extends JpaRepository<AssociatedSubform, Integer> {


    List<AssociatedSubform> findByStepUuid(FormStep stepUuid);





}
