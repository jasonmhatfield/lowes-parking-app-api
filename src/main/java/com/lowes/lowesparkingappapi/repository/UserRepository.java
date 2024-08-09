package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {}
