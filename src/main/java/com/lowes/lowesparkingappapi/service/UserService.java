package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.UserDto;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(UUID userId) {
        return userRepository.findById(userId).map(this::convertToDto)
                .orElse(null);  // Handle the not found case appropriately
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        user = userRepository.save(user);
        return convertToDto(user);
    }

    public UserDto updateUser(UUID userId, UserDto userDto) {
        if (userRepository.existsById(userId)) {
            User user = convertToEntity(userDto);
            user.setUserId(userId);
            user = userRepository.save(user);
            return convertToDto(user);
        }
        return null;  // Handle the not found case appropriately
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public void registerUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        userRepository.save(user);
    }

    private UserDto convertToDto(User user) {
        return UserDto.builder().userId(user.getUserId()).firstName(user.getFirstName()).lastName(user.getLastName())
                .email(user.getEmail()).role(user.getRole()).hasHandicapPlacard(user.isHasHandicapPlacard())
                .hasEv(user.isHasEv()).build();
    }

    private User convertToEntity(UserDto userDto) {
        return User.builder().userId(userDto.getUserId()).firstName(userDto.getFirstName())
                .lastName(userDto.getLastName()).email(userDto.getEmail()).role(userDto.getRole())
                .hasHandicapPlacard(userDto.isHasHandicapPlacard()).hasEv(userDto.isHasEv()).build();
    }
}
