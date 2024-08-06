package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.service.ParkingPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passes")
public class ParkingPassController {

    private final ParkingPassService parkingPassService;

    @Autowired
    public ParkingPassController(ParkingPassService parkingPassService) {
        this.parkingPassService = parkingPassService;
    }

    @PostMapping
    public ResponseEntity<String> assignParkingPass(@RequestBody ParkingPassDto parkingPassDto) {
        parkingPassService.assignParkingPass(parkingPassDto);
        return ResponseEntity.ok("Parking pass assigned successfully");
    }

    @GetMapping
    public ResponseEntity<List<ParkingPassDto>> getAllParkingPasses() {
        List<ParkingPassDto> passes = parkingPassService.getAllParkingPasses();
        return ResponseEntity.ok(passes);
    }
}
