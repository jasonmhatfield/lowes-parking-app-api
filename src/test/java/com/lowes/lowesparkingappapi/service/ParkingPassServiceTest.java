package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.ParkingPassRepository;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import com.lowes.lowesparkingappapi.util.DtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ParkingPassServiceTest {

    @Mock
    private ParkingPassRepository parkingPassRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ParkingPassService parkingPassService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetParkingPassByUserId() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "First", "Last", "email@example.com", "employee", false, false);
        ParkingPass parkingPass = new ParkingPass();
        parkingPass.setPassId(UUID.randomUUID());
        parkingPass.setUser(user);
        parkingPass.setIssueDate(LocalDate.now());
        parkingPass.setExpiryDate(LocalDate.now().plusDays(365));

        when(parkingPassRepository.findByUser_UserId(userId)).thenReturn(Optional.of(parkingPass));

        ParkingPassDto parkingPassDto = parkingPassService.getParkingPassByUserId(userId);

        assertNotNull(parkingPassDto);
        assertEquals(parkingPass.getPassId(), parkingPassDto.getPassId());
        verify(parkingPassRepository, times(1)).findByUser_UserId(userId);
    }

    @Test
    void testAssignParkingPass() {
        ParkingPassDto parkingPassDto = new ParkingPassDto();
        parkingPassDto.setPassId(UUID.randomUUID());
        parkingPassDto.setUserId(UUID.randomUUID());
        parkingPassDto.setIssueDate(LocalDate.now());
        parkingPassDto.setExpiryDate(LocalDate.now().plusDays(365));

        User user = new User(parkingPassDto.getUserId(), "First", "Last", "email@example.com", "employee", false, false);
        when(userRepository.findById(parkingPassDto.getUserId())).thenReturn(Optional.of(user));

        ParkingPass parkingPass = DtoConverter.convertDtoToParkingPass(parkingPassDto, user);

        when(parkingPassRepository.save(any(ParkingPass.class))).thenReturn(parkingPass);

        parkingPassService.assignParkingPass(parkingPassDto);

        verify(parkingPassRepository, times(1)).save(any(ParkingPass.class));
    }

    @Test
    void testGetAllParkingPasses() {
        UUID userId1 = UUID.randomUUID();
        UUID userId2 = UUID.randomUUID();

        User user1 = new User(userId1, "First1", "Last1", "email1@example.com", "employee", false, false);
        User user2 = new User(userId2, "First2", "Last2", "email2@example.com", "employee", false, false);

        ParkingPass parkingPass1 = new ParkingPass();
        parkingPass1.setPassId(UUID.randomUUID());
        parkingPass1.setUser(user1);
        parkingPass1.setIssueDate(LocalDate.now());
        parkingPass1.setExpiryDate(LocalDate.now().plusDays(365));

        ParkingPass parkingPass2 = new ParkingPass();
        parkingPass2.setPassId(UUID.randomUUID());
        parkingPass2.setUser(user2);
        parkingPass2.setIssueDate(LocalDate.now());
        parkingPass2.setExpiryDate(LocalDate.now().plusDays(365));

        when(parkingPassRepository.findAll()).thenReturn(List.of(parkingPass1, parkingPass2));

        List<ParkingPassDto> parkingPassDtos = parkingPassService.getAllParkingPasses();

        assertNotNull(parkingPassDtos);
        assertEquals(2, parkingPassDtos.size());
        verify(parkingPassRepository, times(1)).findAll();
    }
}
