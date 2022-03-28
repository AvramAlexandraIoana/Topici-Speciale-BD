package com.awbd.proiect.services;

import com.awbd.proiect.domain.Country;
import com.awbd.proiect.repositories.AgencyRepository;
import com.awbd.proiect.repositories.CountryRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
    CountryService countryService;

    @Mock
    CountryRepository countryRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        countryService = new CountryServiceImpl(countryRepository);
    }

    @Test
    @DisplayName("Adaugarea unei tari")
    public void createTest() {
        //arrange
        Country country = new Country("Romania");
        Country savedCountry = new Country(1L, "Romania");
        when(countryRepository.save(country)).thenReturn(savedCountry);

        //act
        Country result = countryService.save(country);

        //assert
        assertEquals(country.getCountryName(), result.getCountryName());

        verify(countryRepository, times(1)).save(country);
    }

    @Test
    @DisplayName("Afisarea tarilor")
    public void getTest(){
        //arrange
        when(countryRepository.findAll()).thenReturn(
                Arrays.asList(new Country(1L, "Romania"))
        );

        //act
        List<Country> result = countryService.findAll();

        //assert
        Country country = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(country.getId(), 1L);
        assertEquals(country.getCountryName(), "Romania");

        verify(countryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Gaseste tara dupa id")
    public void getByIdTest(){
        //arrange
        Country country = new Country(1L, "Romania");
        when(countryRepository.findById(country.getId())).thenReturn(Optional.of(country));

        //act
        Country result = countryService.findById(country.getId());

        //assert
        assertEquals(country.getId(), 1L);
        assertEquals(country.getCountryName(), result.getCountryName());

        verify(countryRepository, times(1)).findById(country.getId());
    }


    @Test
    @DisplayName("Updatarea unei tari")
    public void updateTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        when(countryRepository.findById(country.getId())).thenReturn(Optional.of(country));
        when(countryRepository.save(country)).thenReturn(country);

        //act
        Country result = countryService.update(country);

        //assert
        assertEquals(country.getId(), 1L);
        assertEquals(country.getCountryName(), result.getCountryName());

        verify(countryRepository, times(1)).findById(country.getId());
        verify(countryRepository, times(1)).save(country);
    }


    @Test
    @DisplayName("Stergerea unei tari")
    public void deleteTest() {
        //arrange
        Country country = new Country(1L, "Romania");

        //act
        countryService.deleteById(country.getId());

        verify(countryRepository, times(1)).deleteById(country.getId());
    }

}
