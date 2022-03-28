package com.awbd.proiect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {

    @NotBlank(message = "Country name cannot be blank!")
    @NotNull(message = "Country name cannot be null!")
    private String countryName;


}
