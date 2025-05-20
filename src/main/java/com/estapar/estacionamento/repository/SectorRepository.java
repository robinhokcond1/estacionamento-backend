package com.estapar.estacionamento.repository;

import com.estapar.estacionamento.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, String> {
}
