package com.lowes.lowesparkingappapi.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingPassDtoTest {

    @Test
    void testParkingPassDto() {
        UUID userId = UUID.randomUUID();
        LocalDate issueDate = LocalDate.now();
        LocalDate expiryDate = issueDate.plusYears(1);

        ParkingPassDto dto = ParkingPassDto.builder().userId(userId).issueDate(issueDate).expiryDate(expiryDate)
                .build();

        assertEquals(userId, dto.getUserId());
        assertEquals(issueDate, dto.getIssueDate());
        assertEquals(expiryDate, dto.getExpiryDate());
    }

    @Test
    void testParkingPassDtoDataAnnotation() {
        UUID userId = UUID.randomUUID();
        LocalDate issueDate = LocalDate.now();
        LocalDate expiryDate = issueDate.plusYears(1);

        ParkingPassDto dto = new ParkingPassDto();
        dto.setUserId(userId);
        dto.setIssueDate(issueDate);
        dto.setExpiryDate(expiryDate);

        assertEquals(userId, dto.getUserId());
        assertEquals(issueDate, dto.getIssueDate());
        assertEquals(expiryDate, dto.getExpiryDate());
    }
}
