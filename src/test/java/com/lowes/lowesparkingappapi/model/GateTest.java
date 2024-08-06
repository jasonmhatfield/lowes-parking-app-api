package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GateTest {

    @Test
    void testSetAndGetGateId() {
        Gate gate = new Gate();
        UUID gateId = UUID.randomUUID();
        gate.setGateId(gateId);

        assertNotNull(gate.getGateId());
        assertEquals(gateId, gate.getGateId());
    }

    @Test
    void testSetAndGetGateName() {
        Gate gate = new Gate();
        String gateName = "Main Entrance";
        gate.setGateName(gateName);

        assertNotNull(gate.getGateName());
        assertEquals(gateName, gate.getGateName());
    }

    @Test
    void testSetAndGetIsOperational() {
        Gate gate = new Gate();
        gate.setIsOperational(true);

        assertTrue(gate.getIsOperational());
    }

    @Test
    void testSetAndGetIsRestricted() {
        Gate gate = new Gate();
        gate.setIsRestricted(true);

        assertTrue(gate.getIsRestricted());
    }

    @Test
    void testSetAndGetLot() {
        Gate gate = new Gate();
        ParkingLot lot = new ParkingLot();
        lot.setLotId(UUID.randomUUID());
        gate.setLot(lot);

        assertNotNull(gate.getLot());
        assertEquals(lot.getLotId(), gate.getLot().getLotId());
    }
}
