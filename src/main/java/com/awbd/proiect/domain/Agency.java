package com.awbd.proiect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Trip> tripList;

    public Agency() {

    }

    public Agency(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Agency(Long id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

}
