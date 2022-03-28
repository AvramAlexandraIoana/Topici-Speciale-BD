package com.awbd.proiect.services;

import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Location;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationService {
    List<Location> findAll();
    Location findById(Long l);
    Location save(Location location);
    Location update(Location location);
    void deleteById(Long id);
    Page<Location> findPage(int currentPage, int pageLimit);
    List<Location> findByCity(String city);
    List<Location> findByCountry(Long countryId);

}
