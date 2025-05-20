package com.estapar.estacionamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    @ManyToOne
    private Sector sector;

    private LocalDateTime entryTime;

    private LocalDateTime parkedTime;

    private LocalDateTime exitTime;

    private Double priceApplied;

    private Boolean finished = false;

}
