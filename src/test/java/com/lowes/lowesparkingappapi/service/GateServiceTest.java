package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.repository.GateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class GateServiceTest {

    @Autowired
    private GateService gateService;

    @MockBean
    private GateRepository gateRepository;

    private Gate gate;

    @BeforeEach
    public void setUp() {
        gate = new Gate();
        gate.setId(1L);
        gate.setGateName("Main Gate");
        gate.setOperational(true);
    }

    @Test
    public void testGetAllGates() {
        Mockito.when(gateRepository.findAll()).thenReturn(Collections.singletonList(gate));

        List<Gate> gates = gateService.getAllGates();
        assertNotNull(gates);
        assertEquals(1, gates.size());
        assertEquals(gate.getId(), gates.get(0).getId());
    }

    @Test
    public void testUpdateGateStatus_Found() {
        Mockito.when(gateRepository.findById(anyLong())).thenReturn(Optional.of(gate));
        Mockito.when(gateRepository.save(any(Gate.class))).thenReturn(gate);

        Gate updatedGate = gateService.updateGateStatus(1L, false);
        assertNotNull(updatedGate);
        assertFalse(updatedGate.isOperational());
        assertEquals(gate.getId(), updatedGate.getId());
    }

    @Test
    public void testUpdateGateStatus_NotFound() {
        Mockito.when(gateRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> gateService.updateGateStatus(999L, false));

        assertEquals("Gate not found with id: 999", exception.getMessage());
    }
}
