package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.ParkingPassRepository;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import com.lowes.lowesparkingappapi.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    public ParkingPassDto getParkingPassByUserId(UUID userId) {
        Optional<ParkingPass> parkingPassOpt = parkingPassRepository.findByUser_UserId(userId);
        if (!parkingPassOpt.isPresent()) {
            return null; // Return null if no parking pass is found
        }
        ParkingPass parkingPass = parkingPassOpt.get();
        return DtoConverter.convertParkingPassToDto(parkingPass);
    }

    public void assignParkingPass(ParkingPassDto parkingPassDto) {
        Optional<User> userOpt = userRepository.findById(parkingPassDto.getUserId());
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found for ID: " + parkingPassDto.getUserId());
        }
        ParkingPass parkingPass = DtoConverter.convertDtoToParkingPass(parkingPassDto, userOpt.get());
        parkingPassRepository.save(parkingPass);
    }

    public List<ParkingPassDto> getAllParkingPasses() {
        List<ParkingPass> parkingPasses = parkingPassRepository.findAll();
        return parkingPasses.stream()
                .filter(pass -> pass.getUser() != null) // Ensure the user is not null
                .map(DtoConverter::convertParkingPassToDto)
                .collect(Collectors.toList());
    }
}
