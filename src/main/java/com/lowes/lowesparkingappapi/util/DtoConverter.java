package com.lowes.lowesparkingappapi.util;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.dto.UserDto;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.ParkingSpace;
import com.lowes.lowesparkingappapi.model.User;

public class DtoConverter {

    public static ParkingPassDto convertToDto(ParkingPass pass) {
        ParkingPassDto dto = new ParkingPassDto();
        dto.setUserId(pass.getUser().getUserId());
        dto.setIssueDate(pass.getIssueDate());
        dto.setExpiryDate(pass.getExpiryDate());
        return dto;
    }

    public static ParkingSpaceDto convertToDto(ParkingSpace space) {
        ParkingSpaceDto dto = new ParkingSpaceDto();
        dto.setSpaceId(space.getSpaceId());
        dto.setFloorId(space.getFloor().getFloorId());
        dto.setSpaceNumber(space.getSpaceNumber());
        dto.setOccupied(space.isOccupied());
        dto.setType(space.getType());
        return dto;
    }

    public static UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setHasHandicapPlacard(user.isHasHandicapPlacard());
        userDto.setHasEv(user.isHasEv());
        return userDto;
    }
}
