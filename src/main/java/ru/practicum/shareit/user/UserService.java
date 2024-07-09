package ru.practicum.shareit.user;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User create(User user);

    User getById(long userId);

    User update(long userId, User user);

    void deleteById(long userId);
}
