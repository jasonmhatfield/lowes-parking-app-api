package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.ParkingPassRepository;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingPassServiceTest {

    @Mock
    private ParkingPassRepository parkingPassRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ParkingPassService parkingPassService;

    private UUID userId;
    private UUID passId;
    private User user;
    private ParkingPass parkingPass;
    private ParkingPassDto parkingPassDto;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        passId = UUID.randomUUID();
        user = new User(userId, "John", "Doe", "john.doe@example.com", "employee", false, false);
        parkingPass = new ParkingPass();
        parkingPass.setPassId(passId);
        parkingPass.setUser(user);
        parkingPass.setIssueDate(LocalDate.now());
        parkingPass.setExpiryDate(LocalDate.now().plusDays(365));
        parkingPassDto = new ParkingPassDto(passId, userId, LocalDate.now(), LocalDate.now().plusDays(365));
    }

    @Test
    void testGetParkingPassByUserId() {
        when(parkingPassRepository.findByUser_UserId(userId)).thenReturn(Optional.of(parkingPass));

        ParkingPassDto result = parkingPassService.getParkingPassByUserId(userId);

        assertNotNull(result);
        assertEquals(passId, result.getPassId());
        verify(parkingPassRepository, times(1)).findByUser_UserId(userId);
    }

    @Test
    void testGetParkingPassByUserIdNotFound() {
        when(parkingPassRepository.findByUser_UserId(userId)).thenReturn(Optional.empty());

        ParkingPassDto result = parkingPassService.getParkingPassByUserId(userId);

        assertNull(result);
        verify(parkingPassRepository, times(1)).findByUser_UserId(userId);
    }

    @Test
    void testAssignParkingPass() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        parkingPassService.assignParkingPass(parkingPassDto);

        verify(parkingPassRepository, times(1)).save(any(ParkingPass.class));
    }

    @Test
    void testAssignParkingPassUserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parkingPassService.assignParkingPass(parkingPassDto));

        assertEquals("User not found for ID: " + userId, exception.getMessage());
        verify(parkingPassRepository, never()).save(any(ParkingPass.class));
    }

    @Test
    void testUpdateParkingPass() {
        when(parkingPassRepository.findById(passId)).thenReturn(Optional.of(parkingPass));

        boolean result = parkingPassService.updateParkingPass(passId, parkingPassDto);

        assertTrue(result);
        verify(parkingPassRepository, times(1)).findById(passId);
        verify(parkingPassRepository, times(1)).save(parkingPass);
    }

    @Test
    void testUpdateParkingPassNotFound() {
        when(parkingPassRepository.findById(passId)).thenReturn(Optional.empty());

        boolean result = parkingPassService.updateParkingPass(passId, parkingPassDto);

        assertFalse(result);
        verify(parkingPassRepository, times(1)).findById(passId);
        verify(parkingPassRepository, never()).save(any(ParkingPass.class));
    }

    @Test
    void testGetAllParkingPasses() {
        when(parkingPassRepository.findAll()).thenReturn(List.of(parkingPass));

        List<ParkingPassDto> result = parkingPassService.getAllParkingPasses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(passId, result.get(0).getPassId());
        verify(parkingPassRepository, times(1)).findAll();
    }
}
