package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GateTest {

    @Test
    public void testNoArgsConstructor() {
        Gate gate = new Gate();
        assertNotNull(gate);
    }

    @Test
    public void testAllArgsConstructor() {
        Gate gate = new Gate(1L, "Main Gate", true);
        assertEquals(1L, gate.getId());
        assertEquals("Main Gate", gate.getGateName());
        assertTrue(gate.isOperational());
    }

    @Test
    public void testSettersAndGetters() {
        Gate gate = new Gate();
        gate.setId(1L);
        gate.setGateName("Main Gate");
        gate.setOperational(true);

        assertEquals(1L, gate.getId());
        assertEquals("Main Gate", gate.getGateName());
        assertTrue(gate.isOperational());
    }
}
