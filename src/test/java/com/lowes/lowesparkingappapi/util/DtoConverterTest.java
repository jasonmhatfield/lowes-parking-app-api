package com.lowes.lowesparkingappapi.util;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.dto.UserDto;
import com.lowes.lowesparkingappapi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DtoConverterTest {

    private ParkingPass parkingPass;
    private ParkingSpace parkingSpace;
    private User user;
    private Floor floor;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(UUID.randomUUID());
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setRole("USER");
        user.setHasHandicapPlacard(true);
        user.setHasEv(false);

        parkingPass = new ParkingPass();
        parkingPass.setPassId(UUID.randomUUID());
        parkingPass.setUser(user);
        parkingPass.setIssueDate(LocalDate.now());
        parkingPass.setExpiryDate(LocalDate.now().plusDays(30));

        floor = new Floor();
        floor.setFloorId(UUID.randomUUID());
        floor.setFloorNumber(1);

        parkingSpace = new ParkingSpace();
        parkingSpace.setSpaceId(UUID.randomUUID());
        parkingSpace.setFloor(floor);
        parkingSpace.setSpaceNumber("A1");
        parkingSpace.setOccupied(false);
        parkingSpace.setType(ParkingSpaceType.REGULAR);
    }

    @Test
    void testConvertToParkingPassDto() {
        ParkingPassDto dto = DtoConverter.convertToDto(parkingPass);

        assertEquals(parkingPass.getUser().getUserId(), dto.getUserId());
        assertEquals(parkingPass.getIssueDate(), dto.getIssueDate());
        assertEquals(parkingPass.getExpiryDate(), dto.getExpiryDate());
    }

    @Test
    void testConvertToParkingSpaceDto() {
        ParkingSpaceDto dto = DtoConverter.convertToDto(parkingSpace);

        assertEquals(parkingSpace.getSpaceId(), dto.getSpaceId());
        assertEquals(parkingSpace.getFloor().getFloorId(), dto.getFloorId());
        assertEquals(parkingSpace.getSpaceNumber(), dto.getSpaceNumber());
        assertEquals(parkingSpace.isOccupied(), dto.isOccupied());
        assertEquals(parkingSpace.getType(), dto.getType());
    }

    @Test
    void testConvertToUserDto() {
        UserDto dto = DtoConverter.convertToDto(user);

        assertEquals(user.getUserId(), dto.getUserId());
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getLastName(), dto.getLastName());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getRole(), dto.getRole());
        assertEquals(user.isHasHandicapPlacard(), dto.isHasHandicapPlacard());
        assertEquals(user.isHasEv(), dto.isHasEv());
    }

    @Test
    void testDtoConverterConstructor() {
        DtoConverter converter = new DtoConverter();
        assertNotNull(converter, "Constructor should not be null");
    }
}
