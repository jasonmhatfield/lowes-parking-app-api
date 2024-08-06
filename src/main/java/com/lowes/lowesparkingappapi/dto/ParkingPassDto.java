package com.lowes.lowesparkingappapi.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ParkingPassDto {
    private UUID userId;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}
