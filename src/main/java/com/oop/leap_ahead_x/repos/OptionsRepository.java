package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Options;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OptionsRepository extends JpaRepository<Options, UUID> {
}
