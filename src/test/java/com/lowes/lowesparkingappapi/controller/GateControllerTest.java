package com.lowes.lowesparkingappapi.controller;

import com.lowes.lowesparkingappapi.model.Gate;
import com.lowes.lowesparkingappapi.service.GateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GateControllerTest {

    @Mock
    private GateService gateService;

    @InjectMocks
    private GateController gateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllGates_shouldReturnListOfGates() {
        Gate gate = new Gate(1L, "Main Gate", true);
        when(gateService.getAllGates()).thenReturn(Collections.singletonList(gate));

        List<Gate> gates = gateController.getAllGates();

        assertEquals(1, gates.size());
        assertEquals("Main Gate", gates.get(0).getGateName());
    }

    @Test
    void updateGateStatus_shouldReturnUpdatedGate() {
        Gate gate = new Gate(1L, "Main Gate", true);
        when(gateService.updateGateStatus(1L, false)).thenReturn(gate);

        Gate updatedGate = gateController.updateGateStatus(1L, false);

        assertEquals(gate, updatedGate);
    }
}
