package com.lowes.lowesparkingappapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {

    @Test
    void parkingSpotModel_shouldWorkCorrectly() {
        // Test AllArgsConstructor
        ParkingSpot spot = new ParkingSpot(1L, "A1", false, "regular", null);

        assertEquals(1L, spot.getId());
        assertEquals("A1", spot.getSpotNumber());
        assertFalse(spot.isOccupied());
        assertEquals("regular", spot.getType());
        assertNull(spot.getUserId());

        // Test NoArgsConstructor and Setters
        ParkingSpot spot2 = new ParkingSpot();
        spot2.setId(2L);
        spot2.setSpotNumber("B2");
        spot2.setOccupied(true);
        spot2.setType("handicap");
        spot2.setUserId(3L);

        assertEquals(2L, spot2.getId());
        assertEquals("B2", spot2.getSpotNumber());
        assertTrue(spot2.isOccupied());
        assertEquals("handicap", spot2.getType());
        assertEquals(3L, spot2.getUserId());

        // Test Getters
        assertEquals(2L, spot2.getId());
        assertEquals("B2", spot2.getSpotNumber());
        assertTrue(spot2.isOccupied());
        assertEquals("handicap", spot2.getType());
        assertEquals(3L, spot2.getUserId());
    }
}
