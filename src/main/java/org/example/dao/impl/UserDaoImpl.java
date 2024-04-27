package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.db.Storage;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public User crated(User user) {
        user.setId(generateUserId());
        Storage.usersStorage.put(user.getId(), user);
        return user;
    }

    @Override
    public User updated(User user) {
        Storage.usersStorage.replace(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return Storage.usersStorage.values().stream()
                .toList();
    }

    @Override
    public Optional<User> getId(Long id) {
        return Optional.ofNullable(Storage.usersStorage.get(id));
    }

    @Override
    public List<User> getByBirthDate(LocalDate bf, LocalDate af) {
        Predicate<User> findUserBirthDate = user -> (user.getBrithDate().isEqual(bf)
                || user.getBrithDate().isAfter(bf)) && (user.getBrithDate().isEqual(af)
                || user.getBrithDate().isBefore(af));
        return Storage.usersStorage.values().stream()
                .filter(findUserBirthDate)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Storage.usersStorage.remove(id);
    }

    private Long generateUserId() {
        return Storage.usersStorage.values()
                .stream()
                .map(User::getId)
                .mapToLong(id -> id)
                .max()
                .orElse(0L) + 1;
    }
}
