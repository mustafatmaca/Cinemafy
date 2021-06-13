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
public class Cinema{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cinema", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cinema", cascade = CascadeType.ALL)
    private Set<Salon> salons = new HashSet<>();

}
