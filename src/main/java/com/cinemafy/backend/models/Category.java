package com.cinemafy.backend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String genre;

    @ManyToMany
    private Set<Film> films = new HashSet<>();
}
