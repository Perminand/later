package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.error.DuplicatedDataException;
import ru.practicum.shareit.exception.error.EntityNotFoundException;
import ru.practicum.shareit.exception.error.ValidationException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public User create(User user) {
        if (user.getEmail() == null) {
            throw new ValidationException("Должен быть емайл");
        }
        validate(user);
        return userRepository.create(user);
    }

    @Override
    public User getById(long userId) {
        User user = userRepository.getById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found id :" + userId));
        return user;
    }

    @Override
    public User update(long userId, User user) {
        User oldUser = getById(userId);
        if (user.getEmail() != null) {
            if (!oldUser.getEmail().equals(user.getEmail())) {
                validate(user);
                oldUser.setEmail(user.getEmail());
            }
        }
        if (user.getName() != null) {
            oldUser.setName(user.getName());
        }
        return userRepository.update(oldUser);
    }

    @Override
    public void deleteById(long userId) {
        userRepository.deleteById(userId);
    }

    private void validate(User user) {
        if(Pattern.matches("^(.+)@(\\S+)$", user.getEmail())) {
            Optional<User> newUser = getAllUsers()
                    .stream()
                    .filter(u -> Objects.equals(u.getEmail(), user.getEmail()))
                    .findFirst();
            if (newUser.isPresent()) {
                throw new DuplicatedDataException("Email " + user.getEmail() + " уже существует");
            }
        } else {
            throw new ValidationException("Емайл не коррктен");
        }
    }
}
