package com.awbd.proiect.services;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Info;
import com.awbd.proiect.domain.Location;
import com.awbd.proiect.repositories.AgencyRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgencyServiceTest {
    AgencyService agencyService;

    @Mock
    AgencyRepository agencyRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        agencyService = new AgencyServiceImpl(agencyRepository);
    }

    @Test
    @DisplayName("Adaugarea unei agentii in repo")
    public void createTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location(1L,"Bucuresti", "Str.Unirii", country, info);
        Agency agency = new Agency(1L,"Ego Travel Romania", location);
        Agency savedAgency = new Agency(1L, "Ego Travel Romania", location);
        when(agencyRepository.save(agency)).thenReturn(savedAgency);

        //act
        Agency result = agencyService.save(agency);

        //assert
        assertEquals(agency.getId(), result.getId());
        assertEquals(agency.getName(), result.getName());
        assertEquals(agency.getLocation().getId(), result.getLocation().getId());

        verify(agencyRepository, times(1)).save(agency);
    }

    @Test
    @DisplayName("Afisarea agentiilor")
    public void getTest(){
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location(1L,"Bucuresti", "Str.Unirii", country, info);
        when(agencyRepository.findAll()).thenReturn(
                Arrays.asList(new Agency(1L, "Ego Travel Romania", location))
        );

        //act
        List<Agency> result = agencyService.findAll();

        //assert
        Agency agency = result.get(0);
        assertEquals(result.size(), 1);
        assertEquals(agency.getId(), 1L);
        assertEquals(agency.getName(), "Ego Travel Romania");
        assertEquals(agency.getLocation().getId(), 1);
    }

    @Test
    @DisplayName("Gaseste agentie dupa id")
    public void getByIdTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location(1L,"Bucuresti", "Str.Unirii", country, info);
        Agency agency = new Agency(1L, "Ego Travel Romania", location);
        when(agencyRepository.findById(agency.getId())).thenReturn(Optional.of(agency));

        //act
        Agency result = agencyService.findById(agency.getId());

        //assert
        assertEquals(agency.getId(), 1L);
        assertEquals(agency.getName(), result.getName());
        assertEquals(agency.getLocation().getId(), result.getLocation().getId());

        verify(agencyRepository, times(1)).findById(agency.getId());
    }

    @Test
    @DisplayName("Updatarea unei agentii")
    public void updateTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location(1L,"Bucuresti", "Str.Unirii", country, info);
        Agency agency = new Agency(1L, "Ego Travel Romania", location);
        when(agencyRepository.findById(agency.getId())).thenReturn(Optional.of(agency));
        when(agencyRepository.save(agency)).thenReturn(agency);

        //act
        Agency result = agencyService.update(agency);

        //assert
        assertEquals(agency.getId(), 1L);
        assertEquals(agency.getName(), result.getName());
        assertEquals(agency.getLocation().getId(), result.getLocation().getId());

        verify(agencyRepository, times(1)).findById(agency.getId());
        verify(agencyRepository, times(1)).save(agency);
    }

    @Test
    @DisplayName("Stergerea unei agentii")
    public void deleteTest() {
        //arrange
        Country country = new Country(1L, "Romania");
        Info info = new Info();
        Location location = new Location(1L,"Bucuresti", "Str.Unirii", country, info);
        Agency agency = new Agency(1L, "Ego Travel Romania", location);

        //act
        agencyService.deleteById(location.getId());

        verify(agencyRepository, times(1)).deleteById(agency.getId());
    }






}
