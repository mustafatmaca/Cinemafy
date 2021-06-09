package com.cinemafy.backend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String time;

    @OneToMany(mappedBy = "session")
    private Set<User> users = new HashSet<>();

    @ManyToOne
    private Film film;

    @ManyToOne
    private Salon salon;
}
