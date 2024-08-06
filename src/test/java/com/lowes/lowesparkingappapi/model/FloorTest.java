package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FloorTest {

    @Test
    void testSetAndGetLot() {
        Floor floor = new Floor();
        ParkingLot lot = new ParkingLot();
        lot.setLotId(UUID.randomUUID());

        floor.setLot(lot);

        assertNotNull(floor.getLot());
        assertEquals(lot.getLotId(), floor.getLot().getLotId());
    }

    @Test
    void testSetAndGetGate() {
        Floor floor = new Floor();
        Gate gate = new Gate();
        gate.setGateId(UUID.randomUUID());

        floor.setGate(gate);

        assertNotNull(floor.getGate());
        assertEquals(gate.getGateId(), floor.getGate().getGateId());
    }
}
