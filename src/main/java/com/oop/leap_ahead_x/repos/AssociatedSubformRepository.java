package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.AssociatedSubform;
import com.oop.leap_ahead_x.domain.FormStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface AssociatedSubformRepository extends JpaRepository<AssociatedSubform, Integer> {
    List<AssociatedSubform> findByStepUuid(FormStep step);

    @Query("SELECT c FROM AssociatedSubform c WHERE c.stepUuid= :sId order by c.position")
    List<AssociatedSubform> findCanvasByStep(@Param("sId") FormStep sId);

}
