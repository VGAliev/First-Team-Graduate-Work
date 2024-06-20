package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;



    @Override
    public void setPassword() {

    }

    @Override
    public UserEntity getUser(String email) {
        return null;
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser) {
        return null;
    }

    @Override
    public void updateUserImage() {

    }
}
