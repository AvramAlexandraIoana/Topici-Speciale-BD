package com.awbd.proiect.dto;

import com.awbd.proiect.domain.Trip;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserTripRequest {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Min(value = 1, message = "Id-ul  trebuie sa fie mai mare decat 0!")
    private Long id;

    @NotNull(message = "Lista de excursii nu poate fi null!")
    private List<Long> tripIds;
}
