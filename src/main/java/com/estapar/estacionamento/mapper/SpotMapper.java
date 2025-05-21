package com.estapar.estacionamento.mapper;

import com.estapar.estacionamento.dto.SpotResponseDTO;
import com.estapar.estacionamento.model.Spot;

public class SpotMapper {
    public static SpotResponseDTO toDTO(Spot spot) {
        SpotResponseDTO dto = new SpotResponseDTO();
        dto.setId(spot.getId());
        dto.setLat(spot.getLat());
        dto.setLng(spot.getLng());
        dto.setOccupied(spot.getOccupied());
        dto.setSectorName(spot.getSector().getName());
        return dto;
    }
}
