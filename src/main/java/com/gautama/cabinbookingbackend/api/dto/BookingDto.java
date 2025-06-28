package com.gautama.cabinbookingbackend.api.dto;

import com.gautama.cabinbookingbackend.api.enums.BookingStatus;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class BookingDto {
    private Long cabinId;
    private String cabinName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status;
}
