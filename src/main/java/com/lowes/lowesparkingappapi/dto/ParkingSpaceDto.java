package com.lowes.lowesparkingappapi.dto;

import com.lowes.lowesparkingappapi.model.ParkingSpaceType;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ParkingSpaceDto {
    private UUID spaceId;
    private UUID floorId;
    private String spaceNumber;
    private boolean isOccupied;
    private ParkingSpaceType type;
}
