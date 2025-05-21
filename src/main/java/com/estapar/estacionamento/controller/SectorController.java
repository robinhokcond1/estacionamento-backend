package com.estapar.estacionamento.controller;

import com.estapar.estacionamento.dto.SectorResponseDTO;
import com.estapar.estacionamento.mapper.SectorMapper;
import com.estapar.estacionamento.repository.SectorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/sectors")
@Tag(name = "Setores", description = "Operações relacionadas aos setores da garagem")
public class SectorController {

    private final SectorRepository sectorRepository;

    public SectorController(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @GetMapping
    @Operation(summary = "Lista todos os setores")
    public ResponseEntity<List<SectorResponseDTO>> findAll() {
        log.info("Buscando todos os setores");
        var result = sectorRepository.findAll()
                .stream()
                .map(SectorMapper::toDTO)
                .collect(Collectors.toList());
        log.debug("Setores encontrados: {}", result.size());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um setor pelo ID")
    public ResponseEntity<SectorResponseDTO> findById(@PathVariable String id) {
        log.info("Buscando setor pelo ID: {}", id);
        return sectorRepository.findById(id)
                .map(SectorMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Setor com ID {} não encontrado", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
