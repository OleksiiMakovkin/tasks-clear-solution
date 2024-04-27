package org.example.dto.mapper;

import org.example.model.User;

public interface PatchRequestDtoMapper<D, T> {
    T ToUpdateModel(D dto, User user);
}
