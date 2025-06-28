package com.gautama.cabinbookingbackend.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Email должен быть корректным")
    @Size(max = 100, message = "Длина email не должна превышать 100 символов")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!?.])[A-Za-z\\d!?.]{8,64}$",
            message = "Только латинские символы, цифры, знаки только !?. Обязательно наличие минимум 1 буквы верхнего и нижнего регистра, цифры и знака."
    )
    @Size(min = 8, max = 100, message = "Длина пароля должна быть от 8 до 100 символов")
    private String password;
}
