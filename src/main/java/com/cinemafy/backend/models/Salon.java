package com.cinemafy.backend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;
    private String seatCapacity;

    @OneToMany(mappedBy = "salon")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "salon")
    private Set<Session> sessions = new HashSet<>();

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Film film;


}
