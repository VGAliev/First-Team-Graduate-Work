package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public UserEntity saveNewUser(UserEntity user) {
        if (getUser(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean setPassword(NewPassword newPassword) {
        UserEntity user = currentUser();
        if (encoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            user.setPassword(newPassword.getNewPassword());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDto getUser() {
        UserEntity user = currentUser();
        if (user != null) {
            return mapper.toUserDto(user);
        }
        return null;
    }

    @Override
    public UserEntity getUser(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser) {
        UserEntity user = currentUser();
        if (updateUser != null) {
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhone(updateUser.getPhone());
            userRepository.save(currentUser());
        }
        return mapper.toUpdateUser(user);
    }

    @Override
    public void updateUserImage(MultipartFile image) {
        if (image != null) {
            UserEntity user = currentUser();
            user.setImage((Image) image);
            userRepository.save(user);
        }
    }

    private UserEntity currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getUser(((UserDetails) authentication.getPrincipal()).getUsername());
    }
}
