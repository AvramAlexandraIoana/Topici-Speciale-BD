package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.dto.AgencyRequest;
import com.awbd.proiect.dto.AgencyUpdate;
import com.awbd.proiect.mapper.AgencyMapper;
import com.awbd.proiect.services.AgencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/agency")
public class AgencyController {
    private AgencyService agencyService;

    private AgencyMapper agencyMapper;


    public AgencyController(AgencyService agencyService, AgencyMapper agencyMapper) {
        this.agencyService = agencyService;
        this.agencyMapper = agencyMapper;
    }


    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Agency>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(agencyService.findAll());
    }

    @PostMapping()
    public
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    ResponseEntity<Agency> save(@RequestBody   @Valid AgencyRequest agencyRequest) {
        Agency savedAgency = agencyService.save(agencyMapper.agencyRequestToAgency(agencyRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("agency/" + savedAgency.getId())
                .build(savedAgency.getId()))
                .body(savedAgency);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    public ResponseEntity<Agency> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(agencyService.findById(id));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    public ResponseEntity<Agency> update(@RequestBody  @Valid AgencyUpdate agencyUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(agencyService.update(agencyMapper.agencyUpdateToAgency(agencyUpdate)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')")
    public void deleteById(@PathVariable Long id) {
        agencyService.deleteById(id);
    }

    @GetMapping("/findByLocation")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Agency>> findByLocation(@RequestParam Long locationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(agencyService.findByLocation(locationId));
    }



}
