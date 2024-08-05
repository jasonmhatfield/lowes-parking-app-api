package com.lowes.lowesparkingappapi.dto;

import com.lowes.lowesparkingappapi.model.ParkingSpaceType;
import lombok.Data;

import java.util.UUID;

@Data
public class ParkingSpaceDto {
    private UUID spaceId;
    private UUID floorId;
    private String spaceNumber;
    private boolean isOccupied;
    private ParkingSpaceType type;
}
