package com.estapar.estacionamento.service;

import com.estapar.estacionamento.dto.GarageResponse;
import com.estapar.estacionamento.dto.SectorDTO;
import com.estapar.estacionamento.dto.SpotDTO;
import com.estapar.estacionamento.exception.GarageLoadException;
import com.estapar.estacionamento.model.Sector;
import com.estapar.estacionamento.model.Spot;
import com.estapar.estacionamento.repository.SectorRepository;
import com.estapar.estacionamento.repository.SpotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@Slf4j
@Service
public class GarageService {

    private final RestTemplate restTemplate;
    private final SectorRepository sectorRepository;
    private final SpotRepository spotRepository;

    @Value("${garage.api.url}")
    private String garageUrl;

    public GarageService(
            RestTemplate restTemplate,
            SectorRepository sectorRepository,
            SpotRepository spotRepository
    ) {
        this.restTemplate = restTemplate;
        this.sectorRepository = sectorRepository;
        this.spotRepository = spotRepository;
    }

    public void carregarDadosDaGaragem() {
        log.info("Iniciando carga de dados da garagem a partir da URL: {}", garageUrl);

        try {
            GarageResponse response = restTemplate.getForObject(garageUrl, GarageResponse.class);
            if (response == null) {
                log.error("A resposta da API de garagem veio nula.");
                throw new GarageLoadException("Resposta da API de garagem veio nula.");
            }

            log.info("Total de setores recebidos: {}", response.getGarage().size());
            for (SectorDTO s : response.getGarage()) {
                Sector sector = new Sector(
                        s.getSector(),
                        s.getBasePrice(),
                        s.getMax_capacity(),
                        LocalTime.parse(s.getOpen_hour()),
                        LocalTime.parse(s.getClose_hour()),
                        s.getDuration_limit_minutes(),
                        0
                );
                sectorRepository.save(sector);
                log.debug("Setor salvo: {}", sector.getName());
            }

            log.info("Total de vagas recebidas: {}", response.getSpots().size());
            for (SpotDTO sp : response.getSpots()) {
                Spot spot = new Spot();
                spot.setLat(sp.getLat());
                spot.setLng(sp.getLng());
                spot.setOccupied(false);

                Sector sector = sectorRepository.findById(sp.getSector())
                        .orElseThrow(() -> new GarageLoadException("Setor não encontrado para ID: " + sp.getSector()));
                spot.setSector(sector);

                spotRepository.save(spot);
                log.debug("Vaga salva no setor {}: ({}, {})", sector.getName(), sp.getLat(), sp.getLng());
            }

            log.info("Carga de dados da garagem finalizada com sucesso.");

        } catch (Exception e) {
            log.error("Erro ao carregar dados da garagem: {}", e.getMessage(), e);
            throw new GarageLoadException("Erro ao carregar dados da garagem.", e);
        }
    }
}
