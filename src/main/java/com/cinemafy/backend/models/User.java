package com.cinemafy.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
