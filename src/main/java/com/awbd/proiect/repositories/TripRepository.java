package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Trip;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TripRepository extends PagingAndSortingRepository<Trip, Long> {
}
