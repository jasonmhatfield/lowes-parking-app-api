package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.AlertDto;
import com.lowes.lowesparkingappapi.exception.UserNotFoundException;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlertService {

    private final UserRepository userRepository;

    @Autowired
    public AlertService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AlertDto> getAlertsForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Logic to generate alerts based on user attributes
        // For example, check if parking is limited based on user's EV or handicap status
        return List.of(new AlertDto("Parking is limited for your category."));
    }
}
