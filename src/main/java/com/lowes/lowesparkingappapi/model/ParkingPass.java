package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ParkingPass {

    @Id
    @GeneratedValue
    private UUID passId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate issueDate;
    private LocalDate expiryDate;
}
