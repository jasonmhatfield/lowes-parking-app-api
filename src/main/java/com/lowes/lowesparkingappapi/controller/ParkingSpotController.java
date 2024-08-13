package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.ParkingSpot;
import com.lowes.lowesparkingappapi.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService, SimpMessagingTemplate messagingTemplate) {
        this.parkingSpotService = parkingSpotService;
        this.messagingTemplate = messagingTemplate;
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

        boolean updated = false;

        if (updates.containsKey("occupied")) {
            Boolean isOccupied = (Boolean) updates.get("occupied");
            Long userId = null;

            if (isOccupied && updates.containsKey("userId")) {
                Object userIdObject = updates.get("userId");
                if (userIdObject != null) {
                    userId = ((Number) userIdObject).longValue();
                }
            }

            spot.setOccupied(isOccupied);
            spot.setUserId(isOccupied ? userId : null);
            updated = true;
        }

        if (updated) {
            ParkingSpot updatedSpot = parkingSpotService.saveParkingSpot(spot);
            messagingTemplate.convertAndSend("/topic/parkingSpots", updatedSpot);
            return ResponseEntity.ok(updatedSpot);
        }

        return ResponseEntity.badRequest().build();
    }
}
