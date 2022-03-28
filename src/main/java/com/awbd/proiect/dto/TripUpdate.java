package com.awbd.proiect.dto;

import com.awbd.proiect.domain.Agency;
import com.awbd.proiect.domain.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TripUpdate {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Min(value = 1, message = "Id-ul  trebuie sa fie mai mare decat 0!")
    private Long id;

    @NotBlank(message = "Numele nu poate fi gol!")
    @NotNull(message = "Numele nu poate fi null!")
    private String name;

    @NotNull(message = "Price nu poate fi null!")
    @Range(min = 1, message= "Pretul trebuie sa fie mai mare decat 0!")
    private Double price;

    @NotNull(message = "Numarul de locuri  nu poate fi null!")
    @Range(min = 1, message= "Numarul de locuri trebuie sa fie mai mare decat 0!")
    private int numberOfSeats;

    @NotNull(message = "Durata nu poate fi null!")
    @Range(min = 1, message= "Durata trebuie sa fie mai mare decat 0!")
    private int duration;

    @NotNull(message = "Data de start nu poate fi null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "Data de final nu poate fi null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;


    @NotNull(message = "Locatia nu poate fi null!")
    private Location location;

    @NotNull(message = "Agentia nu poate fi null!")
    private Agency agency;


}
