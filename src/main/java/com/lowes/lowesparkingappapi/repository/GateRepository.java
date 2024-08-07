package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GateRepository extends JpaRepository<Gate, UUID> {
}
