package org.example.configuration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class setupData {
    private final UserService userService;

    @PostConstruct
    public void setup() {
        User oneUser = new User("task1@gmail.com", "Igor", "Shevchenko", LocalDate.of(1994, 3, 21), "Shevchenka 1", "+380684572457");
        userService.create(oneUser);

        User twoUser = new User("task1@gmail.com", "Igor", "Shevchenko", LocalDate.of(1994, 3, 21), "Shevchenka 1", "+380684572457");
        userService.create(twoUser);
    }
}
