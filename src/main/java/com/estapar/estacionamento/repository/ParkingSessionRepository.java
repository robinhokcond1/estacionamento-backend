package com.estapar.estacionamento.repository;

import com.estapar.estacionamento.model.ParkingSession;
import com.estapar.estacionamento.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    Optional<ParkingSession> findByLicensePlateAndFinishedFalse(String licensePlate);

    List<ParkingSession> findBySectorAndExitTimeBetween(Sector sector, LocalDateTime start, LocalDateTime end);
}
