package com.awbd.proiect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private int numberOfSeats;
    private int duration;
    private Date startDate;
    private Date endDate;


    private Long userId;

    @ManyToOne
    @JoinColumn(name="agency_id")
    private Agency agency;


    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToMany(mappedBy = "trips",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @ToString.Exclude
    private List<User> users;

    public Trip() {
    }

    public Trip(String name, int numberOfSeats, Double price, int duration, Date startDate, Date endDate, Location location, Agency agency, Long userId) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.agency = agency;
        this.userId = userId;
    }

    public Trip(Long id, String name, int numberOfSeats, Double price, int duration, Date startDate, Date endDate, Location location, Agency agency, Long userId) {
        this.id = id;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.agency = agency;
        this.userId = userId;
    }

}
