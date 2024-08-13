package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateRepository extends JpaRepository<Gate, Long> {}
