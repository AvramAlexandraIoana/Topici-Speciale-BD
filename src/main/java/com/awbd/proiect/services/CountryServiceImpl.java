package com.awbd.proiect.services;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Country;
import com.awbd.proiect.repositories.AgencyRepository;
import com.awbd.proiect.repositories.CountryRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl  implements  CountryService{

    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public
    List<Country> findAll(){
        List<Country> countryList = new LinkedList<>();
        countryRepository.findAll().iterator().forEachRemaining(countryList::add);
        logger.info("S-au preluat tarile {}", countryList);
        return countryList;
    }

    @Override
    public Country findById(Long l) {
        Optional<Country> productOptional =
                countryRepository.findById(l);
        if (!productOptional.isPresent()) {
            throw new ObjectNotFoundException("Tara nu a fost gasita!");
        }
        logger.info("S-a preluat tara cu id-ul " + l + " {}", productOptional.get());
        return productOptional.get();
    }

    @Override
    public Country save(Country country) {
        Country savedCountry = countryRepository.save(country);
        logger.info("S-a adaugat tara {}", savedCountry);
        return savedCountry;
    }

    @Override
    public Country update(Country country) {
        Optional<Country> productOptional =
                countryRepository.findById(country.getId());
        if (!productOptional.isPresent()) {
            throw new ObjectNotFoundException("Tara nu a fost gasita!");
        }
        Country updatedCountry = countryRepository.save(country);
        logger.info("S-a updatat tara cu id-ul " +  updatedCountry.getId() + " {}", updatedCountry);
        return updatedCountry;
    }

    @Override
    public void deleteById(Long id) {
        logger.info("S-a sters tara cu id-ul "  + id);
        countryRepository.deleteById(id);
    }




}
