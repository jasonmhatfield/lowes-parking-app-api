package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.model.ParkingSpace;
import com.lowes.lowesparkingappapi.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public List<ParkingSpace> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll();
    }
}
