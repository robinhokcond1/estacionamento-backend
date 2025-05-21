package com.estapar.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpotResponseDTO {
    private Long id;
    private Double lat;
    private Double lng;
    private boolean occupied;
    private String sectorName;

}
