package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.repository.GateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GateServiceTest {

    @Mock
    private GateRepository gateRepository;

    @InjectMocks
    private GateService gateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllGates_shouldReturnListOfGates() {
        Gate gate = new Gate(1L, "Main Gate", true);
        when(gateRepository.findAll()).thenReturn(Collections.singletonList(gate));

        List<Gate> gates = gateService.getAllGates();

        assertEquals(1, gates.size());
    }

    @Test
    void updateGateStatus_shouldReturnUpdatedGate() {
        Gate gate = new Gate(1L, "Main Gate", true);
        when(gateRepository.findById(1L)).thenReturn(Optional.of(gate));
        when(gateRepository.save(gate)).thenReturn(gate);

        Gate updatedGate = gateService.updateGateStatus(1L, false);

        assertFalse(updatedGate.isOperational());
    }

    @Test
    void updateGateStatus_shouldThrowExceptionIfGateNotFound() {
        when(gateRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gateService.updateGateStatus(1L, false));
    }
}
