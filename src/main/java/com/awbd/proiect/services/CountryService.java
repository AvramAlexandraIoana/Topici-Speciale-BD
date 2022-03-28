package com.awbd.proiect.services;


import com.awbd.proiect.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Country findById(Long l);
    Country save(Country country);
    Country update(Country country);
    void deleteById(Long id);


}
