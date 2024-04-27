package org.example.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserDao;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.crated(user);
    }

    @Override
    public User update(User user) {
        return userDao.updated(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getId(id).orElseThrow(() ->
                new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> getByBirthDate(LocalDate bf, LocalDate af) {
        if (bf.isAfter(af)) {
            throw new RuntimeException(bf + "must be before " + af);
        }
        return userDao.getByBirthDate(bf, af);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
