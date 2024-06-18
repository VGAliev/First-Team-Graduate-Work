package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entity.UserEntity;

public interface UserService {
    void setPassword();

    UserEntity getUser(String email);

    UpdateUser updateUser(UpdateUser updateUser);

    void updateUserImage();
}
