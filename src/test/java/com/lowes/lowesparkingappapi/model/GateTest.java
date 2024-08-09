package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GateTest {

    @Test
    void gateModel_shouldWorkCorrectly() {
        // Test AllArgsConstructor
        Gate gate = new Gate(1L, "Main Gate", true);

        assertEquals(1L, gate.getId());
        assertEquals("Main Gate", gate.getGateName());
        assertTrue(gate.isOperational());

        // Test NoArgsConstructor and Setters
        Gate gate2 = new Gate();
        gate2.setId(2L);
        gate2.setGateName("Secondary Gate");
        gate2.setOperational(false);

        assertEquals(2L, gate2.getId());
        assertEquals("Secondary Gate", gate2.getGateName());
        assertFalse(gate2.isOperational());

        // Test Getters
        assertEquals(2L, gate2.getId());
        assertEquals("Secondary Gate", gate2.getGateName());
        assertFalse(gate2.isOperational());
    }
}
