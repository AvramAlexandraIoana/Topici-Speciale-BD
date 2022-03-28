package com.awbd.proiect.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryName;

    @OneToMany(mappedBy = "country",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
    @JsonIgnore
    @ToString.Exclude
    private List<Location> locationList;


    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country() {

    }

    public Country(Long id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

}
