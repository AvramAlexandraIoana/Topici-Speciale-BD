package com.awbd.proiect.dto;

import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    @NotBlank(message = "Orasul nu poate fi gol!")
    @NotNull(message = "Orasul nu poate fi null!")
    private String city;

    @NotBlank(message = "Adresa nu poate fi goala!")
    @NotNull(message = "Adresa nu poate fi null!")
    private String streetAddress;

    @NotNull(message = "Country nu poate fi null!")
    private Country country;

    private Info info;

}
