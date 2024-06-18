package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.UserEntity;

public interface UserService {
    UserEntity saveUser(UserEntity user);

    boolean setPassword(NewPassword newPassword);

    UserDto getUser();

    UserEntity getUser(String email);

    UpdateUser updateUser(UpdateUser updateUser);

    void updateUserImage();
}
