package com.awbd.proiect.dto;

import com.awbd.proiect.domain.Country;
import com.awbd.proiect.domain.Location;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AgencyRequest {
    @NotBlank(message = "Numele nu poate fi gol!")
    @NotNull(message = "Numele nu poate fi null!")
    private String name;

    @NotNull(message = "Locatia nu poate fi null!")
    private Location location;

    private Long userId;

}
