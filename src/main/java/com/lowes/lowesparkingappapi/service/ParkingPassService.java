package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.ParkingPassRepository;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import com.lowes.lowesparkingappapi.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingPassService {

    @Autowired
    private ParkingPassRepository parkingPassRepository;

    @Autowired
    private UserRepository userRepository;

    public void assignParkingPass(ParkingPassDto parkingPassDto) {
        Optional<User> userOptional = userRepository.findById(parkingPassDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        ParkingPass parkingPass = new ParkingPass();
        parkingPass.setUser(user);
        parkingPass.setIssueDate(LocalDate.now()); // Set issue date to today
        parkingPass.setExpiryDate(LocalDate.now().plusYears(1)); // Set expiry date to one year from today

        parkingPassRepository.save(parkingPass);
    }

    public List<ParkingPassDto> getAllParkingPasses() {
        return parkingPassRepository.findAll().stream()
                .map(DtoConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
