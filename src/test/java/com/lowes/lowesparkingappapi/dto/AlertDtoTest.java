package com.lowes.lowesparkingappapi.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlertDtoTest {

    @Test
    void testAlertDto() {
        String message = "Test Alert";

        AlertDto dto = new AlertDto(message);

        assertEquals(message, dto.getMessage());
    }

    @Test
    void testAlertDtoDataAnnotation() {
        AlertDto dto = new AlertDto();
        dto.setMessage("Test Message");

        assertEquals("Test Message", dto.getMessage());
    }
}
