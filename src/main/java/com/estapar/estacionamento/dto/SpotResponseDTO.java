package com.estapar.estacionamento.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa uma vaga de estacionamento")
public class SpotResponseDTO {

    @Schema(description = "Identificador da vaga", example = "1")
    private Long id;

    @Schema(description = "Latitude da vaga", example = "-23.5505")
    private Double lat;

    @Schema(description = "Longitude da vaga", example = "-46.6333")
    private Double lng;

    @Schema(description = "Indica se a vaga está ocupada", example = "false")
    private boolean occupied;

    @Schema(description = "Nome do setor ao qual a vaga pertence", example = "A")
    private String sectorName;

}
