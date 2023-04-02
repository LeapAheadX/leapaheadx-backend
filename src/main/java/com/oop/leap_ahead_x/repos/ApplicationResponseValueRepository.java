package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ApplicationResponseValueRepository extends JpaRepository<ApplicationResponseValue, Integer> {
    @Query("SELECT a.value FROM ApplicationResponseValue a WHERE a.applicationUuid = :aId AND a.componentUuid = :cId AND a.canvasUuid = :canId")
    String getValue(@Param("aId") Application applicationUuid, @Param("cId") InputComponent componentUuid, @Param("canId") SubformCanvas canvasUuid);

    void deleteByApplicationUuid(Application application);
}
