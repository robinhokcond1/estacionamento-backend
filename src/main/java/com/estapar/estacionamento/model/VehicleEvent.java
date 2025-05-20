package com.estapar.estacionamento.model;

import com.estapar.estacionamento.model.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private LocalDateTime timestamp;

    private Double lat;

    private Double lng;


}
