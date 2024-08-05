package com.lowes.lowesparkingappapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ParkingPassDto {

    private UUID userId;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    public ParkingPassDto() {
    }

    public ParkingPassDto(UUID userId, LocalDate issueDate, LocalDate expiryDate) {
        this.userId = userId;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

}
