package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
