package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GateController {
    @Autowired
    private GateService gateService;

    @GetMapping("/gates")
    public List<Gate> getAllGates() {
        return gateService.getAllGates();
    }

    @PatchMapping("/gates/{id}")
    public Gate updateGateStatus(@PathVariable Long id, @RequestParam boolean isOperational) {
        return gateService.updateGateStatus(id, isOperational);
    }
}
