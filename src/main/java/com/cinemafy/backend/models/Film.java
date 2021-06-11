package com.cinemafy.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
