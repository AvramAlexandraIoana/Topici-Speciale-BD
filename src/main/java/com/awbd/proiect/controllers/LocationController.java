package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Location;
import com.awbd.proiect.dto.LocationRequest;
import com.awbd.proiect.dto.LocationUpdate;
import com.awbd.proiect.mapper.LocationMapper;
import com.awbd.proiect.services.LocationService;
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
@RequestMapping("/api/location")
public class LocationController {
    private LocationService locationService;

    private LocationMapper locationMapper;


    public LocationController(LocationService locationService, LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }


    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public
    ResponseEntity<List<Location>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(locationService.findAll());
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public
    ResponseEntity<Location> save(@RequestBody @Valid LocationRequest locationRequest) {
        Location savedLocation = locationService.save(locationMapper.locationRequestToLocation(locationRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("location/" + savedLocation.getId())
                .build(savedLocation.getId()))
                .body(savedLocation);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Location> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationService.findById(id));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Location> update(@RequestBody  @Valid LocationUpdate locationUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationService.update(locationMapper.locationUpdateToLocation(locationUpdate)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        locationService.deleteById(id);
    }


    @GetMapping("/page/{currentPage}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Location> findPage(@PathVariable int currentPage){
        int pageLimit = 4;
        Page<Location> page = locationService.findPage(currentPage, pageLimit);
        List<Location> locations = page.getContent();

        return locations;

    }

    @GetMapping("/findByCity")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Location>> findByCity(@RequestParam String city) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationService.findByCity(city));
    }

    @GetMapping("/findByCountry")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Location>> findByCountry(@RequestParam Long countryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationService.findByCountry(countryId));
    }

}
