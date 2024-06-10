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

    @Mapping(source = "email", target = "username")
    Register toRegister(UserEntity user);

    @Mapping(source = "username", target = "email")
    UserEntity toUser(Register register);

    @Mapping(source = "email", target = "username")
    Login toLogin(UserEntity user);

    @Mapping(source = "username", target = "email")
    UserEntity toUser(Login login);

    UpdateUser toUpdateUser(UserEntity user);

    UserEntity toUser(UpdateUser updateUser);
}
