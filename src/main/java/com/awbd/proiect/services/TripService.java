package com.awbd.proiect.services;


import com.awbd.proiect.domain.Location;
import com.awbd.proiect.domain.Trip;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TripService {
    List<Trip> findAll();
    Trip findById(Long l);
    Trip save(Trip trip);
    Trip update(Trip trip);
    void deleteById(Long id);
    Page<Trip> findPageSortingByPriceDescending(int currentPage, int pageLimit);

}
