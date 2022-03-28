package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgencyRepository extends CrudRepository<Agency, Long> {
    @Query("select a from Agency a where a.location.id = :locationId")
    List<Agency> findByLocation(@Param(value = "locationId") Long locationId);
}
