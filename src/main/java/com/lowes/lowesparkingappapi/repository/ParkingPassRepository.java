package com.lowes.lowesparkingappapi.repository;


import com.lowes.lowesparkingappapi.model.ParkingPass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkingPassRepository extends JpaRepository<ParkingPass, UUID> {
}
