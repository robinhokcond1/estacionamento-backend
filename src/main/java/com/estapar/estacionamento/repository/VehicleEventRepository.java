package com.estapar.estacionamento.repository;

import com.estapar.estacionamento.model.VehicleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleEventRepository extends JpaRepository<VehicleEvent, Long> {
}

