package com.oop.leap_ahead_x.repos;

import com.oop.leap_ahead_x.domain.Options;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<Options, UUID> {
    @Query("SELECT o FROM Options o JOIN o.optionComponentLinkInputComponents f WHERE f.componentUuid = :cId")
    List<Options> findOptionsByComponent(@Param("cId") UUID cId);
}
