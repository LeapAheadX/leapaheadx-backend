package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.SubformCanvas;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubformCanvasRepository extends JpaRepository<SubformCanvas, UUID> {
}
