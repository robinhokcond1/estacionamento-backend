package com.estapar.estacionamento.service;

import com.estapar.estacionamento.dto.SectorDTO;
import com.estapar.estacionamento.dto.SpotDTO;
import com.estapar.estacionamento.exception.GarageLoadException;
import com.estapar.estacionamento.model.Sector;
import com.estapar.estacionamento.model.Spot;
import com.estapar.estacionamento.repository.SectorRepository;
import com.estapar.estacionamento.repository.SpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GarageServiceTest {

    private GarageService garageService;
    private SectorRepository sectorRepository;
    private SpotRepository spotRepository;

    @BeforeEach
    void setUp() {
        sectorRepository = mock(SectorRepository.class);
        spotRepository = mock(SpotRepository.class);
        garageService = new GarageService(
                new RestTemplate(),
                sectorRepository,
                spotRepository
        );
    }

    @Test
    void deveConverterSectorDTOEmSector() {
        SectorDTO dto = new SectorDTO();
        dto.setSector("A");
        dto.setBasePrice(BigDecimal.valueOf(10.0));
        dto.setMax_capacity(20);
        dto.setOpen_hour("08:00");
        dto.setClose_hour("18:00");
        dto.setDuration_limit_minutes(120);

        Sector result = garageService.mapToSector(dto);

        assertThat(result.getName()).isEqualTo("A");
        assertThat(result.getBasePrice()).isEqualByComparingTo(BigDecimal.valueOf(10.0));
        assertThat(result.getMaxCapacity()).isEqualTo(20);
        assertThat(result.getOpenHour()).isEqualTo(LocalTime.of(8, 0));
        assertThat(result.getCloseHour()).isEqualTo(LocalTime.of(18, 0));
        assertThat(result.getDurationLimitMinutes()).isEqualTo(120);
        assertThat(result.getCurrentOccupation()).isZero();
    }

    @Test
    void deveConverterSpotDTOEmSpotComSetorAssociado() {

        SpotDTO dto = new SpotDTO();
        dto.setLat(-23.55);
        dto.setLng(-46.63);
        dto.setSector("A");

        Sector mockSector = new Sector();
        mockSector.setName("A");
        mockSector.setBasePrice(BigDecimal.TEN);

        when(sectorRepository.findById("A")).thenReturn(Optional.of(mockSector));

        Spot result = garageService.mapToSpot(dto);

        assertThat(result.getLat()).isEqualTo(-23.55);
        assertThat(result.getLng()).isEqualTo(-46.63);
        assertThat(result.getOccupied()).isFalse();
        assertThat(result.getSector()).isEqualTo(mockSector);
        assertThat(result.getSector().getName()).isEqualTo("A");
    }

    @Test
    void deveLancarGarageLoadExceptionQuandoSetorNaoEncontrado() {
        SpotDTO dto = new SpotDTO();
        dto.setLat(-23.55);
        dto.setLng(-46.63);
        dto.setSector("X");

        when(sectorRepository.findById("X")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> garageService.mapToSpot(dto))
                .isInstanceOf(GarageLoadException.class)
                .hasMessageContaining("Setor não encontrado para ID: X");
    }
}
