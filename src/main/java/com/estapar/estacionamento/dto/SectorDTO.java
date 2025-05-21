package com.estapar.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO {
    private String sector;
    private BigDecimal basePrice;
    private Integer max_capacity;
    private String open_hour;
    private String close_hour;
    private Integer duration_limit_minutes;

}
