package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    void setPassword();

    UserDto getUser();

    UpdateUser updateUser(UpdateUser updateUser);

    void updateUserImage();
}
