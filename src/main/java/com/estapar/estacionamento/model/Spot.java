package com.estapar.estacionamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "spots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lat;
    private Double lng;

    @ManyToOne
    @JoinColumn(name = "sector_name")
    private Sector sector;

    private Boolean occupied = false;
}
