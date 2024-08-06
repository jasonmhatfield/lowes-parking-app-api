package com.lowes.lowesparkingappapi.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoTest {

    @Test
    void testUserDto() {
        UUID userId = UUID.randomUUID();
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String role = "USER";
        boolean hasHandicapPlacard = true;
        boolean hasEv = false;

        UserDto dto = UserDto.builder().userId(userId).firstName(firstName).lastName(lastName).email(email).role(role)
                .hasHandicapPlacard(hasHandicapPlacard).hasEv(hasEv).build();

        assertEquals(userId, dto.getUserId());
        assertEquals(firstName, dto.getFirstName());
        assertEquals(lastName, dto.getLastName());
        assertEquals(email, dto.getEmail());
        assertEquals(role, dto.getRole());
        assertEquals(hasHandicapPlacard, dto.isHasHandicapPlacard());
        assertEquals(hasEv, dto.isHasEv());
    }

    @Test
    void testUserDtoDataAnnotation() {
        UUID userId = UUID.randomUUID();
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String role = "USER";
        boolean hasHandicapPlacard = true;
        boolean hasEv = false;

        UserDto dto = new UserDto();
        dto.setUserId(userId);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setRole(role);
        dto.setHasHandicapPlacard(hasHandicapPlacard);
        dto.setHasEv(hasEv);

        assertEquals(userId, dto.getUserId());
        assertEquals(firstName, dto.getFirstName());
        assertEquals(lastName, dto.getLastName());
        assertEquals(email, dto.getEmail());
        assertEquals(role, dto.getRole());
        assertEquals(hasHandicapPlacard, dto.isHasHandicapPlacard());
        assertEquals(hasEv, dto.isHasEv());
    }
}
