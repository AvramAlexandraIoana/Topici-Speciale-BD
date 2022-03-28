package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Location;
import com.awbd.proiect.domain.Trip;
import com.awbd.proiect.dto.LocationRequest;
import com.awbd.proiect.dto.LocationUpdate;
import com.awbd.proiect.dto.TripRequest;
import com.awbd.proiect.dto.TripUpdate;
import com.awbd.proiect.mapper.LocationMapper;
import com.awbd.proiect.mapper.TripMapper;
import com.awbd.proiect.services.LocationService;
import com.awbd.proiect.services.TripService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    private TripService tripService;
    private TripMapper tripMapper;

    public TripController(TripService tripService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.tripMapper = tripMapper;
    }


    @GetMapping("/list")
    public
    ResponseEntity<List<Trip>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(tripService.findAll());
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    public
    ResponseEntity<Trip> save(@RequestBody @Valid TripRequest tripRequest) {
        Trip savedTrip = tripService.save(tripMapper.tripRequestToTrip(tripRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("trip/" + savedTrip.getId())
                .build(savedTrip.getId()))
                .body(savedTrip);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')  || hasRole('ROLE_USER')")
    public ResponseEntity<Trip> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.findById(id));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    public ResponseEntity<Trip> update(@RequestBody @Valid TripUpdate tripUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.update(tripMapper.tripUpdateToTrip(tripUpdate)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    public void deleteById(@PathVariable Long id) {
        tripService.deleteById(id);
    }


    @GetMapping("/page/{currentPage}/{pageLimit}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Trip> findPageSortingByPriceDescending(@PathVariable int currentPage, @PathVariable int pageLimit){
        Page<Trip> page = tripService.findPageSortingByPriceDescending(currentPage, pageLimit);
        List<Trip> trips = page.getContent();
        return trips;

    }

}
