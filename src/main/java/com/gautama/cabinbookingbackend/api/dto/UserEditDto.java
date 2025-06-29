package com.gautama.cabinbookingbackend.api.dto;

import com.gautama.cabinbookingbackend.api.enums.Role;
import lombok.Data;

@Data
public class UserEditDto {
    private String firstName;

    private String lastName;

    private String phone;
}
