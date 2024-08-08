package com.lowes.lowesparkingappapi.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParkingPassDto {
    private UUID passId;
    private UUID userId;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}
