package com.awbd.proiect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryUpdate {

    @NotNull(message = "Id-ul nu poate fi null!")
    @Min(value = 1, message = "Id-ul  trebuie sa fie mai mare decat 0!")
    private Long id;

    @NotBlank(message = "Numele nu poate fi gol!")
    @NotNull(message = "Numele nu poate fi null!")
    private String countryName;
}