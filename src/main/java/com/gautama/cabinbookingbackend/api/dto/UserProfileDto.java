package com.gautama.cabinbookingbackend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfileDto {
    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
