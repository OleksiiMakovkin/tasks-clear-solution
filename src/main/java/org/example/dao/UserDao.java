package org.example.dao;

import org.example.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    User crated (User user);

    User updated (User user);

    List<User> getAll();

    Optional<User> getId (Long id);

    List<User> getByBirthDate (LocalDate bf, LocalDate af);

    void delete (Long id);
}
