package com.cinemafy.backend.models;
/**
 * @author Mustafa Atmaca
 */

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
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String seatCapacity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "salon", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "salon", cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<>();

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Film film;


}
