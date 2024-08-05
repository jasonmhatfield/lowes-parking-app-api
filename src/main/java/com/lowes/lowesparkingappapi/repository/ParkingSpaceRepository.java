package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, UUID> {
}

