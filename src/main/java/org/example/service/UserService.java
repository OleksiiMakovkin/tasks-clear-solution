package org.example.service;

import org.example.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User create(User user);

    User update(User user);

    User getById(Long id);

    List<User> getAll();

    List<User> getByBirthDate(LocalDate bf, LocalDate af);

    void delete(Long id);
}
