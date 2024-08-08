package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import com.lowes.lowesparkingappapi.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotRepository.findAll();
    }

    // New method to get a parking spot by ID
    public ParkingSpot getParkingSpotById(Long id) {
        return parkingSpotRepository.findById(id).orElse(null);
    }

    // New method to save a parking spot
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
