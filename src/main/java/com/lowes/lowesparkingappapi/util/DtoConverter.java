package com.lowes.lowesparkingappapi.util;

import com.lowes.lowesparkingappapi.dto.ParkingPassDto;
import com.lowes.lowesparkingappapi.dto.ParkingSpaceDto;
import com.lowes.lowesparkingappapi.dto.UserDto;
import com.lowes.lowesparkingappapi.model.ParkingPass;
import com.lowes.lowesparkingappapi.model.ParkingSpace;
import com.lowes.lowesparkingappapi.model.User;

public final class DtoConverter {
    public static ParkingPassDto convertToDto(ParkingPass pass) {
        return ParkingPassDto.builder().userId(pass.getUser().getUserId()).issueDate(pass.getIssueDate())
                .expiryDate(pass.getExpiryDate()).build();
    }

    public static ParkingSpaceDto convertToDto(ParkingSpace space) {
        return ParkingSpaceDto.builder().spaceId(space.getSpaceId()).floorId(space.getFloor().getFloorId())
                .spaceNumber(space.getSpaceNumber()).isOccupied(space.isOccupied()).type(space.getType()).build();
    }

    public static UserDto convertToDto(User user) {
        return UserDto.builder().userId(user.getUserId()).firstName(user.getFirstName()).lastName(user.getLastName())
                .email(user.getEmail()).role(user.getRole()).hasHandicapPlacard(user.isHasHandicapPlacard())
                .hasEv(user.isHasEv()).build();
    }
}
