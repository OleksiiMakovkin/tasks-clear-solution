package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.RequestPatchDtoMapper;
import org.example.dto.mapper.UserRequestDtoMapper;
import org.example.dto.mapper.UserResponseDtoMapper;
import org.example.dto.request.UserRequestDto;
import org.example.dto.response.UserResponseDto;
import org.example.model.User;
import org.example.service.UserService;
import org.example.util.DateTimePatternUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private  final UserRequestDtoMapper requestDtoMapper;
    private final UserResponseDtoMapper responseDtoMapper;
    private final RequestPatchDtoMapper requestPatchDtoMapper;

    @PostMapping
    public UserResponseDto create(@RequestBody @Valid UserRequestDto requestDto) {
        User user = userService.create(requestDtoMapper.Model(requestDto));
        return responseDtoMapper.mapToDto(user);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid UserRequestDto requestDto) {
        User user = requestDtoMapper.Model(requestDto);
        user.setId(userService.getById(id).getId());
        return responseDtoMapper.mapToDto(userService.update(user));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return responseDtoMapper.mapToDto(user);
    }

    @GetMapping("/by-birthday")
    public List<UserResponseDto> findUserBirthDate(
            @RequestParam @DateTimeFormat(pattern = DateTimePatternUtil.DATE_PATTERN)
            LocalDate before,
            @RequestParam @DateTimeFormat(pattern = DateTimePatternUtil.DATE_PATTERN) LocalDate after) {
        return userService.getByBirthDate(before, after)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
