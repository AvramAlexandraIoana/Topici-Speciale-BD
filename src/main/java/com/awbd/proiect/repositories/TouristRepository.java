package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Tourist;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TouristRepository extends PagingAndSortingRepository<Tourist, Long> {
}
