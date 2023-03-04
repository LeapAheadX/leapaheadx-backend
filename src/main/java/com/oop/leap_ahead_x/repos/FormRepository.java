package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Form;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormRepository extends JpaRepository<Form, UUID> {
}
