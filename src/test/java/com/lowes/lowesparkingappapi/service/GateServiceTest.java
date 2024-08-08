package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.exception.ResourceNotFoundException;
import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.repository.GateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GateServiceTest {

    @Mock
    private GateRepository gateRepository;

    @InjectMocks
    private GateService gateService;

    private UUID gateId;
    private Gate gate;

    @BeforeEach
    void setUp() {
        gateId = UUID.randomUUID();
        gate = new Gate();
        gate.setGateId(gateId);
        gate.setGateName("Main Gate");
        gate.setIsOperational(true);
    }

    @Test
    void testGetAllGates() {
        List<Gate> gates = List.of(gate);
        when(gateRepository.findAll()).thenReturn(gates);

        List<Gate> result = gateService.getAllGates();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(gateId, result.get(0).getGateId());
        verify(gateRepository, times(1)).findAll();
    }

    @Test
    void testUpdateGateStatus() {
        when(gateRepository.findById(gateId)).thenReturn(Optional.of(gate));
        when(gateRepository.save(any(Gate.class))).thenReturn(gate);

        Gate updatedGate = gateService.updateGateStatus(gateId, false);

        assertNotNull(updatedGate);
        assertFalse(updatedGate.getIsOperational());
        verify(gateRepository, times(1)).findById(gateId);
        verify(gateRepository, times(1)).save(gate);
    }

    @Test
    void testUpdateGateStatusGateNotFound() {
        when(gateRepository.findById(gateId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> gateService.updateGateStatus(gateId, false));

        assertEquals("Gate not found with id: " + gateId, exception.getMessage());
        verify(gateRepository, times(1)).findById(gateId);
        verify(gateRepository, never()).save(any(Gate.class));
    }
}
