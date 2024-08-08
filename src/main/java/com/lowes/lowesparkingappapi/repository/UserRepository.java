package com.lowes.lowesparkingappapi.repository;

import com.lowes.lowesparkingappapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
