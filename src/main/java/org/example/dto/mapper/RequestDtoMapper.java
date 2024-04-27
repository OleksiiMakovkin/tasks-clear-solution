package org.example.dto.mapper;

public interface RequestDtoMapper <D, T> {
    T Model(D dto);
}
