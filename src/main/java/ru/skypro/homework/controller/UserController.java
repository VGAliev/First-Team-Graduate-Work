package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("users")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    //    Обновление пароля (200/401)
    @PostMapping("set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword password) {
        userService.changePassword(password);
        return ResponseEntity.status(200).build();
    }

    //    Получение информации об авторизованном пользователе (200/401)
    @GetMapping("me")
    public ResponseEntity<UserDTO> getMe() {
        UserDTO userDTO = mapper.mapToUserDTO(userService.getMe());
        return ResponseEntity.ok(userDTO);
    }

    // Обновление информации об авторизованном пользователе (200/401)
    @PatchMapping("me")
    public ResponseEntity<UpdateUser> patchMe(@RequestBody UpdateUser user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> setImage(@RequestParam @RequestBody MultipartFile image,
                                          Authentication authentication) throws IOException {
        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            userService.updateImage(username, image);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
