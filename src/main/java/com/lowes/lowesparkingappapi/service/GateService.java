package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.repository.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateService {
    private final GateRepository gateRepository;

    @Autowired
    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }

    public Gate updateGateStatus(Long id, boolean isOperational) {
        Gate gate = gateRepository.findById(id).orElseThrow(() -> new RuntimeException("Gate not found"));
        System.out.println("Updating gate " + gate.getId() + " to isOperational: " + isOperational);
        gate.setOperational(isOperational);
        Gate updatedGate = gateRepository.save(gate);
        System.out.println("Updated gate status: " + updatedGate.isOperational());
        return updatedGate;
    }
}
