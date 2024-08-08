package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.repository.ParkingPassRepository;
import com.lowes.lowesparkingappapi.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingPassService {

    private final ParkingPassRepository parkingPassRepository;

    @Autowired
    public ParkingPassService(ParkingPassRepository parkingPassRepository) {
        this.parkingPassRepository = parkingPassRepository;
    }

    public ParkingPassDto getParkingPassByUserId(UUID userId) {
        Optional<ParkingPass> parkingPassOpt = parkingPassRepository.findByUser_UserId(userId);
        if (!parkingPassOpt.isPresent()) {
            return null; // Return null if no parking pass is found
        }
        ParkingPass parkingPass = parkingPassOpt.get();
        return DtoConverter.convertParkingPassToDto(parkingPass);
    }
}
