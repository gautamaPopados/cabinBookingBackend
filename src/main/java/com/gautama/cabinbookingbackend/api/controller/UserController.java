package com.gautama.cabinbookingbackend.api.controller;

import com.gautama.cabinbookingbackend.api.config.JwtUtil;
import com.gautama.cabinbookingbackend.api.dto.*;
import com.gautama.cabinbookingbackend.core.service.AuthService;
import com.gautama.cabinbookingbackend.core.service.RevokedTokenRedisService;
import com.gautama.cabinbookingbackend.core.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResultDto> register(@Valid @RequestBody UserRegisterDto createDto) {
        UserResultDto userDto = userService.registerUser(createDto);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResultDto> login(@RequestBody UserLoginDto userLogin) {
        try {
            UserResultDto userDto = userService.loginAccount(userLogin);
            return ResponseEntity.ok(userDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UserResultDto("Неверный логин или пароль"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("Вы успешно вышли!");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> profile(HttpServletRequest request) {
        UserProfileDto userDto = userService.userProfile(request);

        return ResponseEntity.ok(userDto);
    }
}

