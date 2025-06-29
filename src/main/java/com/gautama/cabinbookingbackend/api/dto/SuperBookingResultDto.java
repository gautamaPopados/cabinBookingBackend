package com.gautama.cabinbookingbackend.api.dto;

import com.gautama.cabinbookingbackend.api.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperBookingResultDto {
    private Long id;
    private Long userId;
    private Long cabinId;
    private String cabinName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status = BookingStatus.PENDING;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPhone;
}
