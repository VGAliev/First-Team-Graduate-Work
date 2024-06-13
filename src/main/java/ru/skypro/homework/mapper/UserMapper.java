package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(UserEntity user);

    UserEntity toUser(UserDto userDto);

    Register toRegister(UserEntity user);

    UserEntity toUser(Register register);

    Login toLogin(UserEntity user);

    UserEntity toUser(Login login);

    UpdateUser toUpdateUser(UserEntity user);

    UserEntity toUser(UpdateUser updateUser);
}
