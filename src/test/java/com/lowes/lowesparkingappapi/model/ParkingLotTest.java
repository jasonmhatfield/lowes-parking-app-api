package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingLotTest {

    @Test
    void testSetAndGetLotId() {
        ParkingLot parkingLot = new ParkingLot();
        UUID lotId = UUID.randomUUID();
        parkingLot.setLotId(lotId);

        assertNotNull(parkingLot.getLotId());
        assertEquals(lotId, parkingLot.getLotId());
    }

    @Test
    void testSetAndGetName() {
        ParkingLot parkingLot = new ParkingLot();
        String name = "Main Lot";
        parkingLot.setName(name);

        assertNotNull(parkingLot.getName());
        assertEquals(name, parkingLot.getName());
    }
}
