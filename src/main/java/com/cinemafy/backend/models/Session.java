package com.cinemafy.backend.models;
/**
 * @author Mustafa Atmaca
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Salon salon;

    @ManyToOne
    private Cinema cinema;
}
