package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.ParkingSpace;
import com.lowes.lowesparkingappapi.repository.ParkingPassRepository;
import com.lowes.lowesparkingappapi.repository.ParkingSpaceRepository;
import com.lowes.lowesparkingappapi.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingSimulationService {

    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingPassRepository parkingPassRepository;

    @Autowired
    public ParkingSimulationService(ParkingSpaceRepository parkingSpaceRepository, ParkingPassRepository parkingPassRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.parkingPassRepository = parkingPassRepository;
    }

    public List<ParkingSpaceDto> simulateParkingLoad(String day, int hour) {
        resetParkingSpaces();
        int occupancyPercentage = getOccupancyPercentage(day, hour);

        List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();
        List<ParkingPass> parkingPasses = parkingPassRepository.findAll();

        int totalSpaces = parkingSpaces.size();
        int spacesToOccupy = (int) Math.ceil((occupancyPercentage / 100.0) * totalSpaces);

        Map<Integer, List<ParkingSpace>> parkingSpacesByFloor = parkingSpaces.stream()
                .collect(Collectors.groupingBy(space -> space.getFloor().getFloorNumber()));

        List<Integer> sortedFloors = Arrays.asList(4, 5, 6, 7, 8);
        Random random = new Random();
        Set<UUID> occupiedPassIds = new HashSet<>();

        for (int i = 0; i < spacesToOccupy; i++) {
            ParkingSpace space = getRandomSpaceWithWeightedProbabilities(parkingSpacesByFloor, sortedFloors, random);

            if (space != null && !space.isOccupied()) {
                ParkingPass pass = getRandomParkingPass(parkingPasses, occupiedPassIds, random);
                space.setOccupied(true);
                occupiedPassIds.add(pass.getUser().getUserId());
                parkingSpaceRepository.save(space);
            }
        }

        return parkingSpaces.stream().map(DtoConverter::convertParkingSpaceToDto).collect(Collectors.toList());
    }

    ParkingPass getRandomParkingPass(List<ParkingPass> parkingPasses, Set<UUID> occupiedPassIds, Random random) {
        ParkingPass pass;
        do {
            pass = parkingPasses.get(random.nextInt(parkingPasses.size()));
        } while (occupiedPassIds.contains(pass.getUser().getUserId()));
        return pass;
    }

    ParkingSpace getRandomSpaceWithWeightedProbabilities(Map<Integer, List<ParkingSpace>> parkingSpacesByFloor, List<Integer> sortedFloors, Random random) {
        List<ParkingSpace> availableSpaces;
        double rand = random.nextDouble();

        if (rand < 0.9) {
            availableSpaces = getAvailableSpaces(parkingSpacesByFloor, Arrays.asList(4, 5));
            if (!availableSpaces.isEmpty()) {
                return availableSpaces.get(random.nextInt(availableSpaces.size()));
            }
        }

        if (rand < 0.99) {
            availableSpaces = getAvailableSpaces(parkingSpacesByFloor, Arrays.asList(6, 7));
            if (!availableSpaces.isEmpty()) {
                return availableSpaces.get(random.nextInt(availableSpaces.size()));
            }
        }

        availableSpaces = getAvailableSpaces(parkingSpacesByFloor, Collections.singletonList(8));
        if (!availableSpaces.isEmpty()) {
            return availableSpaces.get(random.nextInt(availableSpaces.size()));
        }

        return null;
    }

    List<ParkingSpace> getAvailableSpaces(Map<Integer, List<ParkingSpace>> parkingSpacesByFloor, List<Integer> floors) {
        return floors.stream()
                .flatMap(floor -> Optional.ofNullable(parkingSpacesByFloor.get(floor)).orElse(Collections.emptyList())
                        .stream()).filter(space -> !space.isOccupied()).collect(Collectors.toList());
    }

    int getOccupancyPercentage(String day, int hour) {
        int maxOccupancy = getMaxOccupancyForDay(day);
        return (int) (maxOccupancy * getOccupancyFactorForHour(hour));
    }

    int getMaxOccupancyForDay(String day) {
        Random random = new Random();
        return switch (day) {
            case "Monday", "Friday" -> random.nextInt(21) + 30; // 30-50%
            case "Tuesday", "Thursday" -> random.nextInt(21) + 60; // 60-80%
            case "Wednesday" -> Math.min(random.nextInt(21) + 90, 100); // 90-110%
            default -> 0;
        };
    }

    double getOccupancyFactorForHour(int hour) {
        return switch (hour) {
            case 0, 1, 2, 3, 4, 5 -> 0.01;
            case 6 -> 0.03;
            case 7 -> 0.25;
            case 8 -> 0.8;
            case 9, 10, 11, 12, 13, 14 -> 1.0;
            case 15 -> 0.8;
            case 16 -> 0.6;
            case 17 -> 0.3;
            case 18 -> 0.1;
            case 19, 20 -> 0.05;
            default -> 0.01;
        };
    }

    public void resetParkingSpaces() {
        List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();
        parkingSpaces.forEach(space -> space.setOccupied(false));
        parkingSpaceRepository.saveAll(parkingSpaces);
    }
}
