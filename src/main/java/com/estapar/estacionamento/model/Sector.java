package com.estapar.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "sectors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
public class Sector {

    @Id
    private String name;

    private BigDecimal basePrice;

    private Integer maxCapacity;

    private LocalTime openHour;

    private LocalTime closeHour;

    private Integer durationLimitMinutes;

    private Integer currentOccupation = 0;
}
