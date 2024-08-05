package com.lowes.lowesparkingappapi.service;

import com.lowes.lowesparkingappapi.dto.UserDto;
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

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setHasHandicapPlacard(userDto.isHasHandicapPlacard());
        user.setHasEv(userDto.isHasEv());
        userRepository.save(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(DtoConverter::convertToDto).collect(Collectors.toList());
    }
}
