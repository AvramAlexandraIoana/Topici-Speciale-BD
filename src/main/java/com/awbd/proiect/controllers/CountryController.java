package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.dto.CountryRequest;
import com.awbd.proiect.dto.CountryUpdate;
import com.awbd.proiect.mapper.CountryMapper;
import com.awbd.proiect.services.AgencyService;
import com.awbd.proiect.services.CountryService;
import com.awbd.proiect.services.CountryServiceImpl;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private CountryService countryService;
    private CountryMapper countryMapper;


    public CountryController(CountryService countryService,  CountryMapper countryMapper) {
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public
    ResponseEntity<List<Country>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(countryService.findAll());
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public
    ResponseEntity<Country> save(@RequestBody  @Valid CountryRequest countryRequest) {
        Country savedCountry = countryService.save(countryMapper.countryRequestToCountry(countryRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("country/" + savedCountry.getId())
                .build(savedCountry.getId()))
                .body(savedCountry);

    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Country> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.findById(id));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Country> update(@RequestBody @Valid CountryUpdate countryUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.update(countryMapper.countryUpdateToCountry(countryUpdate)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
    }


}
