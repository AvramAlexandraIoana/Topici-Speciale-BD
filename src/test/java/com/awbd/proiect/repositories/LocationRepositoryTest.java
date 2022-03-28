package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Location;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace =
        AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Slf4j
public class LocationRepositoryTest {
    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void findByCountry() {
        List<Location> locationList = locationRepository.findByCountry(1L);
        assertTrue(locationList.size() >= 1);
        log.info("find by country ...");
        locationList.forEach(location -> {
            log.info(location.getCity());
        });
    }

    @Test
    public void findPage(){
        Pageable firstPage = PageRequest.of(0, 2);
        Page<Location> allLocations = locationRepository.findAll(firstPage);
        Assert.assertTrue(allLocations.getNumberOfElements() == 2);
    }

}


