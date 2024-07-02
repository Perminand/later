package ru.practicum.user;

import java.util.List;
import ru.practicum.user.User;

public interface UserService {
    List<User> getAllUsers();
    User saveUser (User user);
}
