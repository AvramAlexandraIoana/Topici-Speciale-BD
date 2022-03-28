package com.awbd.proiect.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetAddress;
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id")
//    @JsonBackReference
    private Country country;


    @OneToMany(mappedBy = "location",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Agency> agencyList;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Info info;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Trip> tripList;

    public  Location() {

    }

    public Location(String city, String streetAddress, Country country, Info info) {
        this.city = city;
        this.streetAddress = streetAddress;
        this.country = country;
        this.info = info;
    }

    public Location(Long id, String city, String streetAddress, Country country, Info info) {
        this.id = id;
        this.city = city;
        this.streetAddress = streetAddress;
        this.country = country;
        this.info = info;
    }

}
