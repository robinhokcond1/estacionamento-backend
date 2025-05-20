package com.estapar.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpotDTO {
    private Long id;
    private String sector;
    private Double lat;
    private Double lng;

}
