package com.lowes.lowesparkingappapi.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "app_user")
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean hasHandicapPlacard;
    private boolean hasEv;

    // Explicit no-argument constructor
    public User() {
    }

    // All-argument constructor for builder pattern
    public User(UUID userId, String firstName, String lastName, String email, String role, boolean hasHandicapPlacard, boolean hasEv) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.hasHandicapPlacard = hasHandicapPlacard;
        this.hasEv = hasEv;
    }
}
