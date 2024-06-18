package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserEntity saveUser(UserEntity user) {
        if (userRepository.findUserEntityByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean setPassword(NewPassword newPassword) {
        return true;
    }

    @Override
    public UserDto getUser() {
        UserEntity user = userRepository.findUserEntityByEmail(currentUserEmail());
        if (user != null) {
            return mapper.toUserDto(user);
        }
        return null;
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

    private String currentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
}
