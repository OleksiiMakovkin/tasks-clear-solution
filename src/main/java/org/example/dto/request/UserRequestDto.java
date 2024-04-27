package org.example.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.lib.ValidAge;
import org.example.lib.ValidEmail;

import java.time.LocalDate;

@Setter
@Getter
public class UserRequestDto {
@ValidEmail
private String email;
@NotNull(message = "not be null")
private String firstName;
@NotNull(message = "not be null")
private String lastName;
@ValidAge
private LocalDate birthDate;
private String address;
private String phoneNumber;
}
