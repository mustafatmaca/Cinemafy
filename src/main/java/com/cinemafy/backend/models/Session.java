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
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "session", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @ManyToOne
    private Film film;

    @ManyToOne
    private Salon salon;
}
