package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {}
