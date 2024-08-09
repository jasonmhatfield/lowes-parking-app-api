package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import com.lowes.lowesparkingappapi.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;

    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotRepository.findAll();
    }

    public ParkingSpot getParkingSpotById(Long id) {
        return parkingSpotRepository.findById(id).orElse(null);
    }

    public ParkingSpot saveParkingSpot(ParkingSpot spot) {
        return parkingSpotRepository.save(spot);
    }

    public ParkingSpot updateParkingSpot(Long id, boolean isOccupied, Long userId) {
        ParkingSpot spot = parkingSpotRepository.findById(id).orElseThrow();
        spot.setOccupied(isOccupied);
        spot.setUserId(isOccupied ? userId : null);
        return parkingSpotRepository.save(spot);
    }
}
