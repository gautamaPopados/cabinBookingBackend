package com.gautama.cabinbookingbackend.api.dto;

import com.gautama.cabinbookingbackend.api.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingDto {
    private Long cabinId;
    private String cabinName;
    private String userName;
    private String userPhone;
    private String userEmail;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status;
}
