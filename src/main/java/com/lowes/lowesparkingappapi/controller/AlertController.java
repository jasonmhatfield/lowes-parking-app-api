package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.dto.AlertDto;
import com.lowes.lowesparkingappapi.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlertDto>> getAlertsForUser(@PathVariable UUID userId) {
        List<AlertDto> alerts = alertService.getAlertsForUser(userId);
        return ResponseEntity.ok(alerts);
    }
}
