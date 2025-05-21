package com.estapar.estacionamento.controller;

import com.estapar.estacionamento.dto.SpotResponseDTO;
import com.estapar.estacionamento.mapper.SpotMapper;
import com.estapar.estacionamento.repository.SpotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/spots")
public class SpotController {

    private final SpotRepository spotRepository;

    public SpotController(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @GetMapping
    public ResponseEntity<List<SpotResponseDTO>> findAll() {
        log.info("Buscando todas as vagas");
        var result = spotRepository.findAll()
                .stream()
                .map(SpotMapper::toDTO)
                .collect(Collectors.toList());
        log.debug("Total de vagas retornadas: {}", result.size());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpotResponseDTO> findById(@PathVariable Long id) {
        log.info("Buscando vaga pelo ID: {}", id);
        return spotRepository.findById(id)
                .map(SpotMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Vaga com ID {} não encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
