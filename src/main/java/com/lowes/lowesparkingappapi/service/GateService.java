package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.exception.ResourceNotFoundException;
import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.repository.GateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

  public Gate updateGateStatus(UUID gateId, Boolean isOperational) {
    Gate gate = gateRepository.findById(gateId)
        .orElseThrow(() -> new ResourceNotFoundException("Gate not found with id: " + gateId));
    gate.setIsOperational(isOperational);
    return gateRepository.save(gate);
  }
}
