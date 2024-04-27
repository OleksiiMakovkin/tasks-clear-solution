package org.example.dto.mapper;

public interface ResponseDtoMapper <D, T> {
    D mapToDto(T t);
}
