package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.service.ParkingPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/passes")
public class ParkingPassController {

    @Autowired
    private ParkingPassService parkingPassService;

    @PutMapping("/{passId}/renew")
    public ResponseEntity<ParkingPass> renewPass(@PathVariable UUID passId) {
        ParkingPass updatedPass = parkingPassService.renewPass(passId);
        return ResponseEntity.ok(updatedPass);
    }

    @DeleteMapping("/{passId}")
    public ResponseEntity<Void> revokePass(@PathVariable UUID passId) {
        parkingPassService.revokePass(passId);
        return ResponseEntity.noContent().build();
    }
}