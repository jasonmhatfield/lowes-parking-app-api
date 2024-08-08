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
    @Autowired
    private ParkingSpotService parkingSpotService;

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
            spot.setOccupied((Boolean) updates.get("isOccupied"));
        }
        if (updates.containsKey("userId")) {
            Object userIdObj = updates.get("userId");
            if (userIdObj instanceof Integer) {
                spot.setUserId(((Integer) userIdObj).longValue());
            } else if (userIdObj instanceof Long) {
                spot.setUserId((Long) userIdObj);
            }
        }

        ParkingSpot updatedSpot = parkingSpotService.saveParkingSpot(spot);
        return ResponseEntity.ok(updatedSpot);
    }
}
