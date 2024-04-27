package org.example.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.example.dao.impl.UserDaoImpl;
import org.example.db.Storage;
import org.example.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDaoImplTest {
    private static UserDaoImpl userDaoImpl;
            ;
    private static Map<Long, User> storage;

    @BeforeAll
    static void beforeAll() {
        userDaoImpl = new UserDaoImpl();
        storage = new HashMap<>();
    }

    @BeforeEach
    void setUp() {
        User first = new User(1L, "igoruser@gmail.com",
                "Igor", "Shevchenko",
                LocalDate.of(1994, 3, 21),
                "Location 1", "+380111111111");
        storage.put(first.getId(), first);
        userDaoImpl.crated(first);

        User second = new User(2L, "Sergeyuser@gmail.com",
                "Sergey", "Shtompel",
                LocalDate.of(1980, 2, 2),
                "Location 2", "+380222222222");
        storage.put(second.getId(), second);
        userDaoImpl.crated(second);

        User third = new User(3L, "Ivanuser@gmail.com",
                "Ivan", "Bublyk",
                LocalDate.of(2017, 1, 5),
                "Location 3", "+38033333333");
        storage.put(third.getId(), third);
        userDaoImpl.crated(third);
    }

    @Test
    void create_is_ok() {
        User user = new User("Gloriauser@gmail.com",
                "Gloria", "Ivanova",
                LocalDate.of(2021, 9, 4),
                "Address", "4890345");
        userDaoImpl.crated(user);
        storage.put(user.getId(), user);
        Assertions.assertEquals(storage, Storage.usersStorage);
        Assertions.assertEquals(storage.size(), Storage.usersStorage.size());
    }

    @Test
    void update_is_ok() {
        User user = new User(1L, "newuser12121@gmail.com",
                "New", "User",
                LocalDate.of(1999, 2, 25),
                "Location 5", "+380444444444");
        storage.put(user.getId(), user);
        userDaoImpl.updated(user);
        Assertions.assertEquals(storage, Storage.usersStorage);
        Assertions.assertEquals(storage.size(), Storage.usersStorage.size());
    }

    @Test
    void delete_is_ok() {
        storage.remove(1L);
        userDaoImpl.delete(1L);
        Assertions.assertEquals(storage, Storage.usersStorage);
        Assertions.assertEquals(storage.size(), Storage.usersStorage.size());
    }

    @Test
    void findUsersBetweenBirthDate_is_ok() {
        int expected = 2;
        int actual = userDaoImpl.getByBirthDate(LocalDate.of(1980, 2, 3),
                LocalDate.of(2024, 5, 27)).size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAll_is_ok() {
        int expected = storage.size();
        int actual = userDaoImpl.getAll().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUserById_is_ok() {
        User expected = storage.get(1L);
        User actual = userDaoImpl.getId(1L).orElse(new User());
        Assertions.assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
        Storage.usersStorage.clear();
        storage.clear();
    }
}