package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.service.GateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GateControllerTest {

    @Mock
    private GateService gateService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private GateController gateController;

    private Gate gate1;
    private Gate gate2;

    @BeforeEach
    void setUp() {
        gate1 = new Gate();
        gate1.setGateId(UUID.randomUUID());
        gate1.setGateName("Gate 1");
        gate1.setIsOperational(true);

        gate2 = new Gate();
        gate2.setGateId(UUID.randomUUID());
        gate2.setGateName("Gate 2");
        gate2.setIsOperational(false);
    }

    @Test
    void testGetAllGates() {
        List<Gate> gates = Arrays.asList(gate1, gate2);
        when(gateService.getAllGates()).thenReturn(gates);

        ResponseEntity<List<Gate>> response = gateController.getAllGates();

        assertEquals(ResponseEntity.ok(gates), response);
        verify(gateService, times(1)).getAllGates();
    }

    @Test
    void testUpdateGateStatus() {
        UUID gateId = gate1.getGateId();
        when(gateService.updateGateStatus(eq(gateId), any(Boolean.class))).thenReturn(gate1);

        ResponseEntity<Gate> response = gateController.updateGateStatus(gateId, gate1);

        assertEquals(ResponseEntity.ok(gate1), response);
        verify(gateService, times(1)).updateGateStatus(gateId, gate1.getIsOperational());
        verify(messagingTemplate, times(1)).convertAndSend("/topic/gates", gate1);
    }
}
