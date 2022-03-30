package com.awbd.proiect.dto;

import com.awbd.proiect.domain.Location;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AgencyUpdate {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Min(value = 1, message = "Id-ul  trebuie sa fie mai mare decat 0!")
    private Long id;

    @NotBlank(message = "Numele nu poate fi gol!")
    @NotNull(message = "Numele nu poate fi null!")
    private String name;

    @NotNull(message = "Locatia nu poate fi null!")
    private Location location;

    private Long userId;
}
