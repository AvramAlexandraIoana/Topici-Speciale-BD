package com.awbd.proiect.services;

import com.awbd.proiect.domain.Location;
import com.awbd.proiect.domain.Trip;
import com.awbd.proiect.repositories.TripRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService {
    private static final Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);

    TripRepository tripRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public
    List<Trip> findAll(){
        List<Trip> trips = new LinkedList<>();
        tripRepository.findAll().iterator().forEachRemaining(trips::add);
        logger.info("S-au preluat  excursiile {}", trips);
        return trips;
    }

    @Override
    public Trip findById(Long l) {
        Optional<Trip> tripOptional =
                tripRepository.findById(l);
        if (!tripOptional.isPresent()) {
            throw new ObjectNotFoundException("Excursia nu a fost gasita!");
        }
        logger.info("S-a preluat excursia cu id-ul " + l + " {} ", tripOptional.get());
        return tripOptional.get();
    }

    @Override
    public Trip save(Trip trip) {
        Trip savedTrip = tripRepository.save(trip);
        logger.info("S-a adaugat excursia {}", savedTrip);
        return savedTrip;
    }


    @Override
    public Trip update(Trip trip) {
        Optional<Trip> tripOptional =
                tripRepository.findById(trip.getId());
        if (!tripOptional.isPresent()) {
            throw new ObjectNotFoundException("Excursia nu a fost gasita!");
        }
        Trip updatedTrip = tripRepository.save(trip);
        logger.info("S-a updatat excursia cu id-ul " + updatedTrip.getId() + " {}", updatedTrip );
        return updatedTrip;
    }

    @Override
    public void deleteById(Long id) {
        logger.info("S-a sters excursia cu id-ul " + id);
        tripRepository.deleteById(id);
    }

    @Override
    public Page<Trip> findPageSortingByPriceDescending(int currentPage, int pageLimit){
        Pageable pageable = PageRequest.of(currentPage - 1, pageLimit, Sort.by("price").descending());
        logger.info("S-au sortat  excursiile descrescator dupa pret {}", tripRepository.findAll(pageable).getContent());
        return tripRepository.findAll(pageable);

    }


}
