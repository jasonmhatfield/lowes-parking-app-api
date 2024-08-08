package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.service.ParkingPassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/passes")
public class ParkingPassController {

    private final ParkingPassService parkingPassService;

    public ParkingPassController(ParkingPassService parkingPassService) {
        this.parkingPassService = parkingPassService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ParkingPassDto> getParkingPassByUserId(@PathVariable UUID userId) {
        ParkingPassDto parkingPassDto = parkingPassService.getParkingPassByUserId(userId);
        if (parkingPassDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if no pass is found
        }
        return ResponseEntity.ok(parkingPassDto);
    }
}
