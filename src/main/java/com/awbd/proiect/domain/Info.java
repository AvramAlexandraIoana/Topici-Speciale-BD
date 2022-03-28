package com.awbd.proiect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @JsonIgnore
    @ToString.Exclude
    private Byte[] image;
    private String description;

    @OneToOne
    @JsonIgnore
    private Location location;



}
