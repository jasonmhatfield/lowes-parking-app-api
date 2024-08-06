package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class ParkingLot {
    @Id
    @GeneratedValue
    private UUID lotId;
    private String name;
}
