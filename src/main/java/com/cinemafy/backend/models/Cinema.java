package com.cinemafy.backend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Cinema{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String city;

    @OneToMany(mappedBy = "cinema")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "cinema")
    private Set<Salon> salons = new HashSet<>();

}
