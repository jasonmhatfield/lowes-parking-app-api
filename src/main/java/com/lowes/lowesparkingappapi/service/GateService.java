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
        Gate gate = gateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gate not found with id: " + id));
        gate.setOperational(isOperational);
        return gateRepository.save(gate);
    }
}
