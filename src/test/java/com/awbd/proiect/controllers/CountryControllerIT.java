package com.awbd.proiect.controllers;
import com.awbd.proiect.domain.Country;
import com.awbd.proiect.dto.CountryRequest;
import com.awbd.proiect.dto.CountryUpdate;
import com.awbd.proiect.mapper.CountryMapper;
import com.awbd.proiect.repositories.CountryRepository;
import com.awbd.proiect.services.CountryService;
import com.awbd.proiect.services.CountryServiceImpl;
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
public class CountryControllerIT {
    @Autowired
    MockMvc mockMvc;

    CountryService countryService;

    @Autowired
    CountryMapper countryMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    CountryRepository countryRepository;


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        countryService = new CountryServiceImpl(countryRepository);
    }

    @Test
    public void createCountryHappyFlow() throws Exception {
        //arrange
        CountryRequest countryRequest = new CountryRequest("S.U.A");

        //act + assert
        mockMvc.perform(post("/api/country")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjE4ODU5MDcsImV4cCI6MTYyMjQ5MDcwNywicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfQ.SO_ANzAVzOfiPSGWK6u7mFDQHrNFPUaPlQOOWc6eVKuzD88BKPnSJZ2ztFIY5DGJhwFIcqEsr8zieyVuI5wJIw")
                .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.countryName").value(countryRequest.getCountryName()));
    }


    @Test
    public void getCountryHappyFlow() throws Exception {
        //arrange
        Country country = new Country(1L,"S.U.A");

        when(countryService.findAll()).thenReturn(
                Arrays.asList(new Country(1L, "S.U.A"))
        );

        //act + assert
        mockMvc.perform(get("/api/country/list")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjAzODIxOTgsImV4cCI6MTYyMDk4Njk5OCwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiIsIlJPTEVfTUFOQUdFUiJdfQ.Dk0Dk_6XHCZgQcSujEGOxU11dseFEAaaeA-ijHIc1QCxpXPF8OBgfjtCzTS0tmKCtyzrkoLxCWRFKdh5naLnNQBearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjE4ODU5MDcsImV4cCI6MTYyMjQ5MDcwNywicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfQ.SO_ANzAVzOfiPSGWK6u7mFDQHrNFPUaPlQOOWc6eVKuzD88BKPnSJZ2ztFIY5DGJhwFIcqEsr8zieyVuI5wJIw")
                .content(objectMapper.writeValueAsString(Arrays.asList(new Country(1L, "S.U.A")))))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].countryName").value(country.getCountryName()));
    }

    @Test
    public void getByIdCountryHappyFlow() throws Exception {

        //act + assert
        mockMvc.perform(get("/api/country/1")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjE4ODU5MDcsImV4cCI6MTYyMjQ5MDcwNywicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfQ.SO_ANzAVzOfiPSGWK6u7mFDQHrNFPUaPlQOOWc6eVKuzD88BKPnSJZ2ztFIY5DGJhwFIcqEsr8zieyVuI5wJIw"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryName").value("S.U.A"));
    }

    @Test
    public void updateCountryHappyFlow() throws Exception {
        //arrange
        CountryUpdate countryUpdate = new CountryUpdate(2L, "Polonia");


        //act + assert
        mockMvc.perform(put("/api/country/update")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjE4ODU5MDcsImV4cCI6MTYyMjQ5MDcwNywicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfQ.SO_ANzAVzOfiPSGWK6u7mFDQHrNFPUaPlQOOWc6eVKuzD88BKPnSJZ2ztFIY5DGJhwFIcqEsr8zieyVuI5wJIw")
                .content(objectMapper.writeValueAsString(countryUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryName").value(countryUpdate.getCountryName()));
    }


    @Test
    public void deleteCountryHappyFlow() throws Exception {

        //act + assert
        mockMvc.perform(delete("/api/country/delete/1")
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoiaW9hbmEtYWxleGFuZHJhLmF2cmFtQG15LmZtaS51bmlidWMucm8iLCJpYXQiOjE2MjE4ODU5MDcsImV4cCI6MTYyMjQ5MDcwNywicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdfQ.SO_ANzAVzOfiPSGWK6u7mFDQHrNFPUaPlQOOWc6eVKuzD88BKPnSJZ2ztFIY5DGJhwFIcqEsr8zieyVuI5wJIw"))
                .andExpect(status().isOk());
    }




}
