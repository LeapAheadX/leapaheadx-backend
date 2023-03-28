package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Options;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<Options, UUID> {
    List<Options> findByParentInputComponent(UUID cId);
}
