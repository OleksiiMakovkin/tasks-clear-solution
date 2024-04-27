package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate brithDate;
    private String address;
    private String phoneNumber;

    public User(String email, String firstName, String lastName, LocalDate brithDate, String address, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.brithDate = brithDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
