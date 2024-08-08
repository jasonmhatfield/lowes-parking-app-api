package com.lowes.lowesparkingappapi.util;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.dto.UserDto;
import com.lowes.lowesparkingappapi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DtoConverterTest {

    private ParkingPass parkingPass;
    private ParkingSpace parkingSpace;
    private User user;

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

        Floor floor = new Floor();
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

        assertNotNull(parkingSpace.getFloor());
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
    void testConvertDtoToParkingPass() {
        ParkingPassDto dto = DtoConverter.convertToDto(parkingPass);
        ParkingPass entity = DtoConverter.convertDtoToParkingPass(dto, user);

        assertEquals(dto.getPassId(), entity.getPassId());
        assertEquals(dto.getUserId(), entity.getUser().getUserId());
        assertEquals(dto.getIssueDate(), entity.getIssueDate());
        assertEquals(dto.getExpiryDate(), entity.getExpiryDate());
    }

    @Test
    void testConvertDtoToParkingSpace() {
        ParkingSpaceDto dto = DtoConverter.convertToDto(parkingSpace);
        ParkingSpace entity = DtoConverter.convertDtoToParkingSpace(dto);

        assertEquals(dto.getSpaceId(), entity.getSpaceId());
        assertEquals(dto.getSpaceNumber(), entity.getSpaceNumber());
        assertEquals(dto.isOccupied(), entity.isOccupied());
        assertEquals(dto.getType(), entity.getType());
    }

    @Test
    void testConvertDtoToUser() {
        UserDto dto = DtoConverter.convertToDto(user);
        User entity = DtoConverter.convertDtoToUser(dto);

        assertEquals(dto.getUserId(), entity.getUserId());
        assertEquals(dto.getFirstName(), entity.getFirstName());
        assertEquals(dto.getLastName(), entity.getLastName());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getRole(), entity.getRole());
        assertEquals(dto.isHasHandicapPlacard(), entity.isHasHandicapPlacard());
        assertEquals(dto.isHasEv(), entity.isHasEv());
    }

    @Test
    void testConvertUserToEntity() {
        UserDto dto = DtoConverter.convertToDto(user);
        User entity = DtoConverter.convertUserToEntity(dto);

        assertEquals(dto.getUserId(), entity.getUserId());
        assertEquals(dto.getFirstName(), entity.getFirstName());
        assertEquals(dto.getLastName(), entity.getLastName());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getRole(), entity.getRole());
        assertEquals(dto.isHasHandicapPlacard(), entity.isHasHandicapPlacard());
        assertEquals(dto.isHasEv(), entity.isHasEv());
    }

    @Test
    void testConvertToDtoParkingPassWithNullUser() {
        parkingPass.setUser(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> DtoConverter.convertToDto(parkingPass));

        assertEquals("ParkingPass user cannot be null", exception.getMessage());
    }

    @Test
    void testConvertToDtoParkingSpaceWithNullFloor() {
        parkingSpace.setFloor(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> DtoConverter.convertToDto(parkingSpace));

        assertEquals("ParkingSpace floor cannot be null", exception.getMessage());
    }
}
