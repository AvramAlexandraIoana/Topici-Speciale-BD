package com.awbd.proiect.services;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Location;

import java.util.List;

public interface AgencyService {
    List<Agency> findAll();
    Agency findById(Long l);
    Agency save(Agency agency);
    Agency update(Agency agency);
    void deleteById(Long id);
    List<Agency> findByLocation(Long locationId);

}
