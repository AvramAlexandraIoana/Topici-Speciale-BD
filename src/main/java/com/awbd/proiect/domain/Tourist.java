package com.awbd.proiect.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String description;

//    @ManyToMany(mappedBy = "tourists",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Trip> tripList;


}
