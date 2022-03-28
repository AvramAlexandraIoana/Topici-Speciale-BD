package com.awbd.proiect.services;
import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Info;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.repositories.AgencyRepository;
import com.awbd.proiect.repositories.CountryRepository;
import com.awbd.proiect.repositories.LocationRepository;
import com.awbd.proiect.utils.ObjectNotFoundException;
import org.junit.Assert;
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
import org.springframework.data.domain.*;


import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        locationService = new LocationServiceImpl(locationRepository);
    }
    @Test
    @DisplayName("Adaugarea unei locatii in repo")
    public void createTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location("Bucuresti", "Str.Unirii", country, info);
        Location savedLocation = new Location("Bucuresti", "Str.Unirii", country, info);
        when(locationRepository.save(location)).thenReturn(savedLocation);

        //act
        Location result = locationService.save(location);

        //assert
        assertEquals(location.getCity(), result.getCity());
        assertEquals(location.getStreetAddress(), result.getStreetAddress());
        assertEquals(location.getCountry().getId(), result.getCountry().getId());

        verify(locationRepository, times(1)).save(location);
    }


    @Test
    @DisplayName("Afisarea locatiilor")
    public void getTest(){
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        when(locationRepository.findAll()).thenReturn(
                Arrays.asList(new Location("Bucuresti","Str.Unirii",country, info))
        );

        //act
        List<Location> result = locationService.findAll();

        //assert
        Location location = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(location.getCity(), "Bucuresti");
        assertEquals(location.getStreetAddress(), "Str.Unirii");
        assertEquals(location.getCountry().getId(), 1);

        verify(locationRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Gaseste locatie dupa id")
    public void getByIdTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location("Bucuresti", "Str.Unirii", country, info);
        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));

        //act
        Location result = locationService.findById(location.getId());

        //assert
        assertEquals(location.getCity(), result.getCity());
        assertEquals(location.getStreetAddress(), result.getStreetAddress());
        assertEquals(location.getCountry().getId(), result.getCountry().getId());

        verify(locationRepository, times(1)).findById(location.getId());
    }

    @Test
    @DisplayName("Updatarea unei locatii")
    public void updateTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location("Bucuresti", "Str.Unirii", country, info);
        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));
        when(locationRepository.save(location)).thenReturn(location);

        //act
        Location result = locationService.update(location);

        //assert
        assertEquals(location.getCity(), result.getCity());
        assertEquals(location.getStreetAddress(), result.getStreetAddress());
        assertEquals(location.getCountry().getId(), result.getCountry().getId());

        verify(locationRepository, times(1)).findById(location.getId());
        verify(locationRepository, times(1)).save(location);
    }

    @Test
    @DisplayName("Stergerea unei locatii")
    public void deleteTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location(1L,"Bucuresti", "Str.Unirii", country, info);

        //act
       locationService.deleteById(location.getId());

        verify(locationRepository, times(1)).deleteById(location.getId());
    }


}
