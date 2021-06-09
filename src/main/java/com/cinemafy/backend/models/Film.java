package com.cinemafy.backend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String runtime;

    @OneToMany(mappedBy = "film")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "film")
    private Set<Salon> salons = new HashSet<>();

    @OneToMany(mappedBy = "film")
    private Set<Session> sessions = new HashSet<>();

    @ManyToMany
    private Set<Category> categories = new HashSet<>();
}
