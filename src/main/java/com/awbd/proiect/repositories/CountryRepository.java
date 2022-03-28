package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository  extends CrudRepository<Country, Long> {
    List<Country> findByCountryNameLike(String countryName);
    List<Country> findByIdIn(List<Long> ids);

}
