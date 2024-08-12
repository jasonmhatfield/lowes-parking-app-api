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
            System.out.println("Updated ParkingSpot: " + spot.getSpotNumber() + " - isOccupied: " + spot.isOccupied());
        }

        ParkingSpot updatedSpot = parkingSpotService.saveParkingSpot(spot);

        // Broadcast the updated spot to clients
        messagingTemplate.convertAndSend("/topic/parkingSpots", updatedSpot);

        System.out.println("Broadcasting parking spot update: " + updatedSpot);

        // Send notifications
        if (parkingSpotService.isParkingFull()) {
            messagingTemplate.convertAndSend("/topic/notifications", "Parking is full");
        }

        if (parkingSpotService.isEvParkingFull()) {
            messagingTemplate.convertAndSend("/topic/notifications", "EV parking spots are full");
        }

        return ResponseEntity.ok(updatedSpot);
    }
}
