package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.FormComponent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormComponentRepository extends JpaRepository<FormComponent, UUID> {
}
