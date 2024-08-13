package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GateController {
    private final GateService gateService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public GateController(GateService gateService, SimpMessagingTemplate messagingTemplate) {
        this.gateService = gateService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/gates")
    public List<Gate> getAllGates() {
        return gateService.getAllGates();
    }

    @PatchMapping("/gates/{id}")
    public Gate updateGateStatus(@PathVariable Long id, @RequestParam boolean isOperational) {
        Gate updatedGate = gateService.updateGateStatus(id, isOperational);
        System.out.println("Broadcasting gate update: " + updatedGate);
        messagingTemplate.convertAndSend("/topic/gates", updatedGate);
        return updatedGate;
    }
}
