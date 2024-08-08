package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.UserDto;
import com.lowes.lowesparkingappapi.exception.UserNotFoundException;
import com.lowes.lowesparkingappapi.model.User;
import com.lowes.lowesparkingappapi.repository.UserRepository;
import com.lowes.lowesparkingappapi.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(DtoConverter::convertUserToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(DtoConverter::convertUserToDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    public UserDto createUser(UserDto userDto) {
        User user = DtoConverter.convertUserToEntity(userDto);
        user = userRepository.save(user);
        return DtoConverter.convertUserToDto(user);
    }

    public UserDto updateUser(UUID userId, UserDto userDto) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        User user = DtoConverter.convertUserToEntity(userDto);
        user.setUserId(userId);
        user = userRepository.save(user);
        return DtoConverter.convertUserToDto(user);
    }

    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    public UserDto registerUser(UserDto userDto) {
        return createUser(userDto);
    }
}
