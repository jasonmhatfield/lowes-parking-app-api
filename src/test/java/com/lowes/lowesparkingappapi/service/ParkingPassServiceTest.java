package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.exception.UserNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingPassServiceTest {

    @Mock
    private ParkingPassRepository parkingPassRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ParkingPassService parkingPassService;

    private User user;
    private ParkingPass parkingPass;
    private ParkingPassDto parkingPassDto;

    @BeforeEach
    void setUp() {
        UUID userId = UUID.randomUUID();
        user = new User();
        user.setUserId(userId);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setRole("USER");
        user.setHasHandicapPlacard(true);
        user.setHasEv(false);

        parkingPass = new ParkingPass();
        parkingPass.setPassId(UUID.randomUUID());
        parkingPass.setUser(user);
        parkingPass.setIssueDate(LocalDate.now());
        parkingPass.setExpiryDate(LocalDate.now().plusYears(1));

        parkingPassDto = new ParkingPassDto();
        parkingPassDto.setUserId(userId);
        parkingPassDto.setIssueDate(LocalDate.now());
        parkingPassDto.setExpiryDate(LocalDate.now().plusYears(1));
    }

    @Test
    void testAssignParkingPass() {
        when(userRepository.findById(parkingPassDto.getUserId())).thenReturn(Optional.of(user));

        parkingPassService.assignParkingPass(parkingPassDto);

        verify(userRepository, times(1)).findById(parkingPassDto.getUserId());
        verify(parkingPassRepository, times(1)).save(any(ParkingPass.class));
    }

    @Test
    void testAssignParkingPass_UserNotFound() {
        when(userRepository.findById(parkingPassDto.getUserId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> parkingPassService.assignParkingPass(parkingPassDto));

        verify(userRepository, times(1)).findById(parkingPassDto.getUserId());
        verify(parkingPassRepository, times(0)).save(any(ParkingPass.class));
    }

    @Test
    void testGetAllParkingPasses() {
        when(parkingPassRepository.findAll()).thenReturn(List.of(parkingPass));

        List<ParkingPassDto> result = parkingPassService.getAllParkingPasses();

        assertEquals(1, result.size());
        assertEquals(parkingPass.getUser().getUserId(), result.get(0).getUserId());
        assertEquals(parkingPass.getIssueDate(), result.get(0).getIssueDate());
        assertEquals(parkingPass.getExpiryDate(), result.get(0).getExpiryDate());

        verify(parkingPassRepository, times(1)).findAll();
    }
}
