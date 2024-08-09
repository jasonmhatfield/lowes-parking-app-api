package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import com.lowes.lowesparkingappapi.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ParkingSpotController {
    private final ParkingSpotService parkingSpotService;

    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @GetMapping("/parkingSpots")
    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotService.getAllParkingSpots();
    }

    @PatchMapping("/parkingSpots/{id}")
    public ResponseEntity<ParkingSpot> updateParkingSpot(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        ParkingSpot spot = parkingSpotService.getParkingSpotById(id);

        if (spot == null) {
            return ResponseEntity.notFound().build();
        }

        if (updates.containsKey("isOccupied")) {
            Boolean isOccupied = (Boolean) updates.get("isOccupied");
            Long userId = null;

            if (updates.containsKey("userId") && updates.get("userId") != null) {
                userId = ((Number) updates.get("userId")).longValue();
            }

            // Check if the spot is already occupied by another user
            if (isOccupied && spot.isOccupied() && (spot.getUserId() == null || !spot.getUserId().equals(userId))) {
                return ResponseEntity.status(403).body(null);
            }

            spot.setOccupied(isOccupied);
            spot.setUserId(isOccupied ? userId : null);
        }

        ParkingSpot updatedSpot = parkingSpotService.saveParkingSpot(spot);
        return ResponseEntity.ok(updatedSpot);
    }
}
