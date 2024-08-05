package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
public class ParkingSpace {
    @Id
    @GeneratedValue
    private UUID spaceId;

    @ManyToOne
    @JoinColumn(name ="floor_id", nullable = false)
    private Floor floor;

    private String spaceNumber;
    private boolean isOccupied;

    @Enumerated(EnumType.STRING)
    private ParkingSpaceType type;
}
