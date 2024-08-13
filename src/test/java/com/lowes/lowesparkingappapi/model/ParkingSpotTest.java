package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotTest {

    @Test
    public void testNoArgsConstructor() {
        ParkingSpot spot = new ParkingSpot();
        assertNotNull(spot);
    }

    @Test
    public void testAllArgsConstructor() {
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "REGULAR", null);
        assertEquals(1L, spot.getId());
        assertEquals("A1", spot.getSpotNumber());
        assertFalse(spot.isOccupied());
        assertEquals("REGULAR", spot.getType());
        assertNull(spot.getUserId());
    }

    @Test
    public void testSettersAndGetters() {
        ParkingSpot spot = new ParkingSpot();
        spot.setId(1L);
        spot.setSpotNumber("A1");
        spot.setOccupied(false);
        spot.setType("REGULAR");
        spot.setUserId(null);

        assertEquals(1L, spot.getId());
        assertEquals("A1", spot.getSpotNumber());
        assertFalse(spot.isOccupied());
        assertEquals("REGULAR", spot.getType());
        assertNull(spot.getUserId());
    }
}
