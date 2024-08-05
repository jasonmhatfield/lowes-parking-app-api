package com.lowes.lowesparkingappapi.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "app_user")
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
}
