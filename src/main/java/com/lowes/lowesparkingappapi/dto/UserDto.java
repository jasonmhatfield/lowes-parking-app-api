package com.lowes.lowesparkingappapi.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean hasHandicapPlacard;
    private boolean hasEv;
    private ParkingPassDto parkingPass;
}
