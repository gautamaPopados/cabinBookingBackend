package com.gautama.cabinbookingbackend.api.dto;

import com.gautama.cabinbookingbackend.api.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 50, message = "Длина имени должна быть от 2 до 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия не должна быть пустой")
    @Size(min = 2, max = 50, message = "Длина фамилии должна быть от 2 до 50 символов")
    private String lastName;

    @Pattern(
            regexp = "^\\+7\\d{10}$",
            message = "Телефон должен быть в формате +7XXXXXXXXXX"
    )
    @NotBlank(message = "Телефон не должен быть пустым")
    private String phone;

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
