package com.lowes.lowesparkingappapi.util;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.dto.UserDto;
import com.lowes.lowesparkingappapi.model.*;

public final class DtoConverter {

    // Private constructor to prevent instantiation
    private DtoConverter() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static ParkingPassDto convertParkingPassToDto(ParkingPass pass) {
        if (pass.getUser() == null) {
            throw new IllegalArgumentException("ParkingPass user cannot be null");
        }
        return ParkingPassDto.builder()
                .passId(pass.getPassId())
                .userId(pass.getUser().getUserId())
                .issueDate(pass.getIssueDate())
                .expiryDate(pass.getExpiryDate())
                .build();
    }

    public static ParkingSpaceDto convertParkingSpaceToDto(ParkingSpace space) {
        if (space.getFloor() == null) {
            throw new IllegalArgumentException("ParkingSpace floor cannot be null");
        }
        return ParkingSpaceDto.builder()
                .spaceId(space.getSpaceId())
                .floorId(space.getFloor().getFloorId())
                .spaceNumber(space.getSpaceNumber())
                .isOccupied(space.isOccupied())
                .type(space.getType())
                .build();
    }

    public static UserDto convertUserToDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .hasHandicapPlacard(user.isHasHandicapPlacard())
                .hasEv(user.isHasEv())
                .build();
    }

    public static ParkingPass convertDtoToParkingPass(ParkingPassDto passDto, User user) {
        ParkingPass pass = new ParkingPass();
        pass.setPassId(passDto.getPassId());
        pass.setUser(user);
        pass.setIssueDate(passDto.getIssueDate());
        pass.setExpiryDate(passDto.getExpiryDate());
        return pass;
    }

    public static ParkingSpace convertDtoToParkingSpace(ParkingSpaceDto spaceDto) {
        ParkingSpace space = new ParkingSpace();
        space.setSpaceId(spaceDto.getSpaceId());
        space.setSpaceNumber(spaceDto.getSpaceNumber());
        space.setOccupied(spaceDto.isOccupied());
        space.setType(spaceDto.getType());
        return space;
    }

    public static User convertDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setHasHandicapPlacard(userDto.isHasHandicapPlacard());
        user.setHasEv(userDto.isHasEv());
        return user;
    }

    public static ParkingPassDto convertToDto(ParkingPass pass) {
        return convertParkingPassToDto(pass);
    }

    public static ParkingSpaceDto convertToDto(ParkingSpace space) {
        return convertParkingSpaceToDto(space);
    }

    public static UserDto convertToDto(User user) {
        return convertUserToDto(user);
    }

    public static User convertUserToEntity(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .hasHandicapPlacard(userDto.isHasHandicapPlacard())
                .hasEv(userDto.isHasEv())
                .build();
    }
}
