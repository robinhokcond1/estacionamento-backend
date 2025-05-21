package com.estapar.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectorResponseDTO {
    private String name;
    private BigDecimal basePrice;
    private int maxCapacity;
    private LocalTime openHour;
    private LocalTime closeHour;
    private int durationLimitMinutes;
    private int currentOccupation;
}
