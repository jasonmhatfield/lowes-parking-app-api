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
    private final SimpMessagingTemplate messagingTemplate; // For WebSocket messages

    @Autowired
    public ParkingSpotController(ParkingSpotService parkingSpotService, SimpMessagingTemplate messagingTemplate) {
        this.parkingSpotService = parkingSpotService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/parkingSpots")
    public List<ParkingSpot> getAllParkingSpots() {
        List<ParkingSpot> spots = parkingSpotService.getAllParkingSpots();
        spots.forEach(spot -> System.out.println("ParkingSpot: " + spot.getSpotNumber() + " - isOccupied: " + spot.isOccupied()));
        return spots;
    }

    @PatchMapping("/parkingSpots/{id}")
    public ResponseEntity<ParkingSpot> updateParkingSpot(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        System.out.println("Received update request for spot ID: " + id);
        System.out.println("Request payload: " + updates);

        ParkingSpot spot = parkingSpotService.getParkingSpotById(id);

        if (spot == null) {
            return ResponseEntity.notFound().build();
        }

        boolean updated = false;

        if (updates.containsKey("occupied")) { // Check the key
            Boolean isOccupied = (Boolean) updates.get("occupied");
            Long userId = null;

            if (updates.containsKey("userId")) {
                userId = updates.get("userId") != null ? ((Number) updates.get("userId")).longValue() : null;
            }

            // Update the spot's status
            spot.setOccupied(isOccupied);
            spot.setUserId(isOccupied ? userId : null);

            updated = true;

            System.out.println("Updated ParkingSpot: " + spot.getSpotNumber() + " - isOccupied: " + spot.isOccupied() + ", userId: " + spot.getUserId());
        }

        if (updated) {
            ParkingSpot updatedSpot = parkingSpotService.saveParkingSpot(spot);

            // Broadcast the updated spot to clients
            messagingTemplate.convertAndSend("/topic/parkingSpots", updatedSpot);

            System.out.println("Broadcasting parking spot update: " + updatedSpot);

            return ResponseEntity.ok(updatedSpot);
        }

        return ResponseEntity.badRequest().build(); // If no update was made, return a bad request
    }
}
