package org.example.dto.mapper;

import org.example.model.User;
import org.example.dto.request.UserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class RequestPatchDtoMapper implements PatchRequestDtoMapper<UserRequestDto, User> {
    @Override
    public User ToUpdateModel(UserRequestDto dto, User user) {
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getBirthDate() != null) {
            user.setBrithDate(dto.getBirthDate());
        }
        if (dto.getAddress() != null) {
            user.setAddress(dto.getAddress());
        }
        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        return user;
    }
}
