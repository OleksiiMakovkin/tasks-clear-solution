package org.example.dto.mapper;

import org.example.dto.response.UserResponseDto;
import org.example.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDtoMapper implements ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setBirthDate(user.getBrithDate());
        responseDto.setAddress(user.getAddress());
        responseDto.setPhone(user.getPhoneNumber());
        return responseDto;
    }
}
