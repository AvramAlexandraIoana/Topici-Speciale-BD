package com.awbd.proiect.dto;

import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationUpdate {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Min(value = 1, message = "Id-ul  trebuie sa fie mai mare decat 0!")
    private Long id;

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
