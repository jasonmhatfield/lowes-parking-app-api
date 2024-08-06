package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.exception.UserNotFoundException;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.ParkingPassRepository;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import com.lowes.lowesparkingappapi.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingPassService {

    private final ParkingPassRepository parkingPassRepository;
    private final UserRepository userRepository;

    @Autowired
    public ParkingPassService(ParkingPassRepository parkingPassRepository, UserRepository userRepository) {
        this.parkingPassRepository = parkingPassRepository;
        this.userRepository = userRepository;
    }

    public void assignParkingPass(ParkingPassDto parkingPassDto) {
        User user = userRepository.findById(parkingPassDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + parkingPassDto.getUserId()));

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
