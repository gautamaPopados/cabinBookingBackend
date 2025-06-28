package com.gautama.cabinbookingbackend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingRequestDto {
    private Long cabinId;
    private LocalDate startDate;
    private LocalDate endDate;
}