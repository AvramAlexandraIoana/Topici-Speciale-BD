package com.awbd.proiect.services;

import com.awbd.proiect.domain.Location;
import com.awbd.proiect.repositories.LocationRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public  class LocationServiceImpl implements  LocationService{

    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public
    List<Location> findAll(){
        List<Location> locations = new LinkedList<>();
        locationRepository.findAll().iterator().forEachRemaining(locations::add);
        logger.info("S-au preluat  locatiile {}", locations);
        return locations;
    }

    @Override
    public Location findById(Long l) {
        Optional<Location> locationOptional =
                locationRepository.findById(l);
        if (!locationOptional.isPresent()) {
            throw new ObjectNotFoundException("Locatia nu a fost gasita!");
        }
        logger.info("S-a preluat locatia cu id-ul " + l + " {}", locationOptional.get());
        return locationOptional.get();
    }

    @Override
    public Location save(Location location) {
        Location savedLocation = locationRepository.save(location);
        logger.info("S-a adaugat locatia {}", savedLocation);
        return savedLocation;
    }


    @Override
    public Location update(Location location) {
        Optional<Location> locationOptional =
                locationRepository.findById(location.getId());
        if (!locationOptional.isPresent()) {
            throw new ObjectNotFoundException("Locatia nu a fost gasita!");
        }
        Location updatedLocation = locationRepository.save(location);
        logger.info("S-a updatat locatia {}", updatedLocation);
        return updatedLocation;
    }

    @Override
    public void deleteById(Long id) {
        logger.info("S-a sters locatia cu " + id);
        locationRepository.deleteById(id);
    }

    @Override
    public Page<Location> findPage(int currentPage, int pageLimit){
        Pageable pageable = PageRequest.of(currentPage - 1, pageLimit);
        logger.info("S-a preluat locatiile din pagina " + currentPage + " {}", locationRepository.findAll(pageable).getContent());
        return locationRepository.findAll(pageable);

    }

    @Override
    public List<Location> findByCity(String city){
        if (city.equals("null") || city.isEmpty()) {
            return this.findAll();
        }
        List<Location> locations = new LinkedList<>();
        locationRepository.findByCity(city.toLowerCase()).iterator().forEachRemaining(locations::add);
        logger.info("S-au preluat locatiile cu city egal cu  " + city + " {}", locations);
        return locations;
    }

    @Override
    public List<Location> findByCountry(Long countryId){
        List<Location> locations = new LinkedList<>();
        locationRepository.findByCountry(countryId).iterator().forEachRemaining(locations::add);
        logger.info("S-au preluat locatiile cu countryId egal cu  " + countryId + " {}", locations);
        return locations;
    }






}
