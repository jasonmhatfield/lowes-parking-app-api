package com.lowes.lowesparkingappapi.dto;

import com.lowes.lowesparkingappapi.model.ParkingSpaceType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingSpaceDtoTest {

    @Test
    void testParkingSpaceDto() {
        UUID spaceId = UUID.randomUUID();
        UUID floorId = UUID.randomUUID();

        ParkingSpaceDto dto = ParkingSpaceDto.builder().spaceId(spaceId).floorId(floorId).spaceNumber("A1")
                .isOccupied(true).type(ParkingSpaceType.REGULAR).build();

        assertEquals(spaceId, dto.getSpaceId());
        assertEquals(floorId, dto.getFloorId());
        assertEquals("A1", dto.getSpaceNumber());
        assertTrue(dto.isOccupied());
        assertEquals(ParkingSpaceType.REGULAR, dto.getType());
    }

    @Test
    void testParkingSpaceDtoDataAnnotation() {
        UUID spaceId = UUID.randomUUID();
        UUID floorId = UUID.randomUUID();

        ParkingSpaceDto dto = new ParkingSpaceDto();
        dto.setSpaceId(spaceId);
        dto.setFloorId(floorId);
        dto.setSpaceNumber("A1");
        dto.setOccupied(true);
        dto.setType(ParkingSpaceType.REGULAR);

        assertEquals(spaceId, dto.getSpaceId());
        assertEquals(floorId, dto.getFloorId());
        assertEquals("A1", dto.getSpaceNumber());
        assertTrue(dto.isOccupied());
        assertEquals(ParkingSpaceType.REGULAR, dto.getType());
    }
}
