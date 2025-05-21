package com.estapar.estacionamento.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Representa um setor do estacionamento")
public class SectorResponseDTO {

    @Schema(description = "Nome do setor", example = "A")
    private String name;

    @Schema(description = "Preço base por hora no setor", example = "10.50")
    private BigDecimal basePrice;

    @Schema(description = "Capacidade máxima de vagas no setor", example = "25")
    private int maxCapacity;

    @Schema(description = "Horário de abertura do setor", example = "08:00")
    private LocalTime openHour;

    @Schema(description = "Horário de fechamento do setor", example = "18:00")
    private LocalTime closeHour;

    @Schema(description = "Tempo máximo permitido (minutos)", example = "120")
    private int durationLimitMinutes;

    @Schema(description = "Quantidade de vagas atualmente ocupadas", example = "3")
    private int currentOccupation;

}