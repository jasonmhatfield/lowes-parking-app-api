package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.model.Floor;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.ParkingSpace;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.ParkingSpaceRepository;
import com.lowes.lowesparkingappapi.repository.ParkingPassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingSimulationServiceTest {

    @Mock
    private ParkingSpaceRepository parkingSpaceRepository;

    @Mock
    private ParkingPassRepository parkingPassRepository;

    @InjectMocks
    private ParkingSimulationService parkingSimulationService;

    private ParkingSpace parkingSpace1;
    private ParkingSpace parkingSpace2;
    private ParkingSpace parkingSpace3;
    private ParkingPass parkingPass;
    private Floor floor1;
    private Floor floor2;
    private Floor floor3;

    @BeforeEach
    void setUp() {
        floor1 = new Floor();
        floor1.setFloorId(UUID.randomUUID());
        floor1.setFloorNumber(4);

        floor2 = new Floor();
        floor2.setFloorId(UUID.randomUUID());
        floor2.setFloorNumber(6);

        floor3 = new Floor();
        floor3.setFloorId(UUID.randomUUID());
        floor3.setFloorNumber(8);

        parkingSpace1 = new ParkingSpace();
        parkingSpace1.setSpaceId(UUID.randomUUID());
        parkingSpace1.setSpaceNumber("A1");
        parkingSpace1.setOccupied(false);
        parkingSpace1.setFloor(floor1);

        parkingSpace2 = new ParkingSpace();
        parkingSpace2.setSpaceId(UUID.randomUUID());
        parkingSpace2.setSpaceNumber("A2");
        parkingSpace2.setOccupied(false);
        parkingSpace2.setFloor(floor2);

        parkingSpace3 = new ParkingSpace();
        parkingSpace3.setSpaceId(UUID.randomUUID());
        parkingSpace3.setSpaceNumber("A3");
        parkingSpace3.setOccupied(false);
        parkingSpace3.setFloor(floor3);

        parkingPass = new ParkingPass();
        User user = new User();
        user.setUserId(UUID.randomUUID());
        parkingPass.setUser(user);
    }

    @Test
    void testSimulateParkingLoad() {
        when(parkingSpaceRepository.findAll()).thenReturn(List.of(parkingSpace1));
        when(parkingPassRepository.findAll()).thenReturn(List.of(parkingPass));

        List<ParkingSpaceDto> parkingSpaces = parkingSimulationService.simulateParkingLoad("Monday", 8);

        assertEquals(1, parkingSpaces.size());
        verify(parkingSpaceRepository, times(2)).findAll();  // Expecting 2 calls to findAll
        verify(parkingPassRepository, times(1)).findAll();
    }

    @Test
    void testSimulateParkingLoadWithDifferentDayAndHour() {
        when(parkingSpaceRepository.findAll()).thenReturn(List.of(parkingSpace1));
        when(parkingPassRepository.findAll()).thenReturn(List.of(parkingPass));

        List<ParkingSpaceDto> parkingSpaces = parkingSimulationService.simulateParkingLoad("Wednesday", 14);

        assertEquals(1, parkingSpaces.size());
        verify(parkingSpaceRepository, times(2)).findAll();  // Expecting 2 calls to findAll
        verify(parkingPassRepository, times(1)).findAll();
    }

    @Test
    void testGetMaxOccupancyForDay_Monday() {
        int maxOccupancy = parkingSimulationService.getMaxOccupancyForDay("Monday");
        assertEquals(true, maxOccupancy >= 30 && maxOccupancy <= 50);
    }

    @Test
    void testGetMaxOccupancyForDay_Tuesday() {
        int maxOccupancy = parkingSimulationService.getMaxOccupancyForDay("Tuesday");
        assertEquals(true, maxOccupancy >= 60 && maxOccupancy <= 80);
    }

    @Test
    void testGetMaxOccupancyForDay_Wednesday() {
        int maxOccupancy = parkingSimulationService.getMaxOccupancyForDay("Wednesday");
        assertEquals(true, maxOccupancy >= 90 && maxOccupancy <= 100);
    }

    @Test
    void testGetMaxOccupancyForDay_Other() {
        int maxOccupancy = parkingSimulationService.getMaxOccupancyForDay("Sunday");
        assertEquals(0, maxOccupancy);
    }

    @Test
    void testGetOccupancyFactorForHour_EarlyMorning() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(3);
        assertEquals(0.01, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Morning() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(7);
        assertEquals(0.25, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Peak() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(9);
        assertEquals(1.0, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Afternoon() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(15);
        assertEquals(0.8, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Evening() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(18);
        assertEquals(0.1, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Case6() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(6);
        assertEquals(0.03, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Case16() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(16);
        assertEquals(0.6, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Case17() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(17);
        assertEquals(0.3, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Case19() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(19);
        assertEquals(0.05, factor);
    }

    @Test
    void testGetOccupancyFactorForHour_Default() {
        double factor = parkingSimulationService.getOccupancyFactorForHour(23);
        assertEquals(0.01, factor);
    }

    @Test
    void testGetRandomParkingPass() {
        List<ParkingPass> parkingPasses = List.of(parkingPass);
        Set<UUID> occupiedPassIds = new HashSet<>();
        ParkingPass result = parkingSimulationService.getRandomParkingPass(parkingPasses, occupiedPassIds, new Random());

        assertEquals(parkingPass, result);
    }

    @Test
    void testResetParkingSpaces() {
        when(parkingSpaceRepository.findAll()).thenReturn(List.of(parkingSpace1));

        parkingSimulationService.resetParkingSpaces();

        verify(parkingSpaceRepository, times(1)).findAll();
        verify(parkingSpaceRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testGetAvailableSpaces() {
        List<ParkingSpace> availableSpaces = parkingSimulationService.getAvailableSpaces(Map.of(4, List.of(parkingSpace1), 6, List.of(parkingSpace2), 8, List.of(parkingSpace3)), List.of(4, 6, 8));
        assertEquals(3, availableSpaces.size());
    }

    @Test
    void testGetRandomSpaceWithWeightedProbabilities_FirstCondition() {
        // Mock data
        when(parkingSpaceRepository.findAll()).thenReturn(List.of(parkingSpace1, parkingSpace2, parkingSpace3));
        Map<Integer, List<ParkingSpace>> parkingSpacesByFloor = parkingSpaceRepository.findAll().stream()
                .collect(Collectors.groupingBy(space -> space.getFloor().getFloorNumber()));

        List<Integer> sortedFloors = Arrays.asList(4, 5, 6, 7, 8);

        // Mock Random to control the randomness
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.05); // Ensure it falls into the first condition
        when(random.nextInt(anyInt())).thenReturn(0); // Ensure it picks the first available space

        ParkingSpace result = parkingSimulationService.getRandomSpaceWithWeightedProbabilities(parkingSpacesByFloor, sortedFloors, random);

        // Assertions
        assertNotNull(result, "The selected ParkingSpace should not be null");
        assertEquals(parkingSpace1, result, "The selected ParkingSpace should match the expected parkingSpace");
    }

    @Test
    void testGetRandomSpaceWithWeightedProbabilities_SecondCondition() {
        // Mock data
        when(parkingSpaceRepository.findAll()).thenReturn(List.of(parkingSpace1, parkingSpace2, parkingSpace3));
        Map<Integer, List<ParkingSpace>> parkingSpacesByFloor = parkingSpaceRepository.findAll().stream()
                .collect(Collectors.groupingBy(space -> space.getFloor().getFloorNumber()));

        List<Integer> sortedFloors = Arrays.asList(4, 5, 6, 7, 8);

        // Mock Random to control the randomness
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.95); // Ensure it falls into the second condition
        when(random.nextInt(anyInt())).thenReturn(0); // Ensure it picks the first available space

        ParkingSpace result = parkingSimulationService.getRandomSpaceWithWeightedProbabilities(parkingSpacesByFloor, sortedFloors, random);

        // Assertions
        assertNotNull(result, "The selected ParkingSpace should not be null");
        assertEquals(parkingSpace2, result, "The selected ParkingSpace should match the expected parkingSpace");
    }

    @Test
    void testGetRandomSpaceWithWeightedProbabilities_ThirdCondition() {
        // Mock data
        when(parkingSpaceRepository.findAll()).thenReturn(List.of(parkingSpace1, parkingSpace2, parkingSpace3));
        Map<Integer, List<ParkingSpace>> parkingSpacesByFloor = parkingSpaceRepository.findAll().stream()
                .collect(Collectors.groupingBy(space -> space.getFloor().getFloorNumber()));

        List<Integer> sortedFloors = Arrays.asList(4, 5, 6, 7, 8);

        // Mock Random to control the randomness
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.995); // Ensure it falls into the third condition
        when(random.nextInt(anyInt())).thenReturn(0); // Ensure it picks the first available space

        ParkingSpace result = parkingSimulationService.getRandomSpaceWithWeightedProbabilities(parkingSpacesByFloor, sortedFloors, random);

        // Assertions
        assertNotNull(result, "The selected ParkingSpace should not be null");
        assertEquals(parkingSpace3, result, "The selected ParkingSpace should match the expected parkingSpace");
    }

    @Test
    void testGetRandomSpaceWithWeightedProbabilities_NoAvailableSpaces() {
        // Mock data
        when(parkingSpaceRepository.findAll()).thenReturn(Collections.emptyList());
        Map<Integer, List<ParkingSpace>> parkingSpacesByFloor = parkingSpaceRepository.findAll().stream()
                .collect(Collectors.groupingBy(space -> space.getFloor().getFloorNumber()));

        List<Integer> sortedFloors = Arrays.asList(4, 5, 6, 7, 8);

        // Mock Random to control the randomness
        Random random = mock(Random.class);
        when(random.nextDouble()).thenReturn(0.995); // Ensure it falls into the third condition

        ParkingSpace result = parkingSimulationService.getRandomSpaceWithWeightedProbabilities(parkingSpacesByFloor, sortedFloors, random);

        // Assertions
        assertNull(result, "The selected ParkingSpace should be null when no available spaces");
    }
}
