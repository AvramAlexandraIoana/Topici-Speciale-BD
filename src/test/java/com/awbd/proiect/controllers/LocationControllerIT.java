package com.awbd.proiect.controllers;
import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Info;
import com.awbd.proiect.dto.CountryRequest;
import com.awbd.proiect.dto.CountryUpdate;
import com.awbd.proiect.dto.LocationRequest;
import com.awbd.proiect.dto.LocationUpdate;
import com.awbd.proiect.mapper.CountryMapper;
import com.awbd.proiect.mapper.LocationMapper;
import com.awbd.proiect.repositories.CountryRepository;
import com.awbd.proiect.repositories.LocationRepository;
import com.awbd.proiect.services.CountryService;
import com.awbd.proiect.services.CountryServiceImpl;
import com.awbd.proiect.services.LocationService;
import com.awbd.proiect.services.LocationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerIT {
    @Autowired
    MockMvc mockMvc;

    LocationService locationService;

    @Autowired
    LocationMapper locationMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    LocationRepository locationRepository;


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        locationService = new LocationServiceImpl(locationRepository);
    }

    @Test
    public void createLocationHappyFlow() throws Exception {
        //arrange
        Country country = new Country(1L,"S.U.A");
        LocationRequest locationRequest = new LocationRequest("Targu-Jiu", "Str.Mioritei", country, null);

        //act + assert
        mockMvc.perform(post("/api/location")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQ")
                .content(objectMapper.writeValueAsString(locationRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.city").value(locationRequest.getCity()));
    }

    @Test
    public void getLocationsHappyFlow() throws Exception {

        //act + assert
        mockMvc.perform(get("/api/location/list")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQ"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void getByIdLocationHappyFlow() throws Exception {

        //act + assert
        mockMvc.perform(get("/api/location/1")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQ"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Targu Jiu"));
    }

    @Test
    public void updateCountryHappyFlow() throws Exception {
        //arrange
        Country country = new Country(1L,"S.U.A");
        LocationUpdate locationUpdate = new LocationUpdate(2L, "Targu-Jiu", "Str.Mioritei", country, null);


        //act + assert
        mockMvc.perform(put("/api/location/update")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQ")
                .content(objectMapper.writeValueAsString(locationUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value(locationUpdate.getCity()));
    }

    @Test
    public void deleteLocationHappyFlow() throws Exception {

        //act + assert
        mockMvc.perform(delete("/api/location/delete/8")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQ"))
                .andExpect(status().isOk());
    }


}
