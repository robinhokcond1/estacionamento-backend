package com.estapar.estacionamento.mapper;

import com.estapar.estacionamento.dto.SectorResponseDTO;
import com.estapar.estacionamento.model.Sector;

public class SectorMapper {
    public static SectorResponseDTO toDTO(Sector sector) {
        SectorResponseDTO dto = new SectorResponseDTO();
        dto.setName(sector.getName());
        dto.setBasePrice(sector.getBasePrice());
        dto.setMaxCapacity(sector.getMaxCapacity());
        dto.setOpenHour(sector.getOpenHour());
        dto.setCloseHour(sector.getCloseHour());
        dto.setDurationLimitMinutes(sector.getDurationLimitMinutes());
        dto.setCurrentOccupation(sector.getCurrentOccupation());
        return dto;
    }
}
