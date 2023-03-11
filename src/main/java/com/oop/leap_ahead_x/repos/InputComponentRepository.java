package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.InputComponent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InputComponentRepository extends JpaRepository<InputComponent, UUID> {
}
