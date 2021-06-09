package com.cinemafy.backend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Salon salon;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Session session;
}
