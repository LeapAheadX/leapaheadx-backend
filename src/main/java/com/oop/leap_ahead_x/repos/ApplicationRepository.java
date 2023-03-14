package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Application;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {



    List<Application> findByStatus(String status);


}
