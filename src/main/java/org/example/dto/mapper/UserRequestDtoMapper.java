package org.example.dto.mapper;

import org.example.model.User;
import org.example.dto.request.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDtoMapper implements RequestDtoMapper<UserRequestDto, User> {
    @Override
    public User Model(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBrithDate(dto.getBirthDate());
        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }
}
