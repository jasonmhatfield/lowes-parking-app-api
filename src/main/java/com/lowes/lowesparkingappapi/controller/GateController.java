package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gates")
public class GateController {

  @Autowired
  private GateService gateService;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @GetMapping
  public ResponseEntity<List<Gate>> getAllGates() {
    List<Gate> gates = gateService.getAllGates();
    return ResponseEntity.ok(gates);
  }

  @PutMapping("/{gateId}")
  public ResponseEntity<Gate> updateGateStatus(@PathVariable UUID gateId, @RequestBody Gate gate) {
    Gate updatedGate = gateService.updateGateStatus(gateId, gate.getIsOperational());
    messagingTemplate.convertAndSend("/topic/gates", updatedGate); // Send update to WebSocket topic
    return ResponseEntity.ok(updatedGate);
  }
}
