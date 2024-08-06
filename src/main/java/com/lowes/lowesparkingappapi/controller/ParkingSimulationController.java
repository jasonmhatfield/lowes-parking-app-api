package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.service.ParkingSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/simulation")
public class ParkingSimulationController {

    private final ParkingSimulationService parkingSimulationService;

    @Autowired
    public ParkingSimulationController(ParkingSimulationService parkingSimulationService) {
        this.parkingSimulationService = parkingSimulationService;
    }

    @PostMapping("/load")
    public ResponseEntity<List<ParkingSpaceDto>> simulateParkingLoad(@RequestParam String dayOfWeek, @RequestParam int hourOfDay) {
        List<ParkingSpaceDto> parkingSpaces = parkingSimulationService.simulateParkingLoad(dayOfWeek, hourOfDay);
        return ResponseEntity.ok(parkingSpaces);
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetParkingSpaces() {
        parkingSimulationService.resetParkingSpaces();
        return ResponseEntity.ok().build();
    }
}
