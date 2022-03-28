package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Location;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =
        AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Rollback(false)
@Slf4j
public class CountryRepositoryTest {
    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void addCountry() {
        Country country = new Country();
        country.setCountryName("Romania");

        Location location = new Location();
        countryRepository.save(country);
    }

    @Test
    public void findByCountryName() {
       List<Country> countryList =
                countryRepository.findByCountryNameLike("%om%");
       assertFalse(countryList.isEmpty());
       log.info("find by country name like ...");
       countryList.forEach(country -> {
           log.info(country.getCountryName());
       });

    }

    @Test
    public void findByIds() {
        List<Country> countryList =
                countryRepository.findByIdIn(Arrays.asList(1L, 2L));
        assertFalse(countryList.isEmpty());
        log.info("find by ids ... ");
        countryList.forEach(country -> {
            log.info(country.getCountryName());
        });

    }


    @Test
    public void deleteCountry() {
        int id  = 9;
        long id1 = id;
        countryRepository.deleteById(id1);
    }




}
