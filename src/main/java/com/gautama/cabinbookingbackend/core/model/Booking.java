package com.gautama.cabinbookingbackend.core.model;

import com.gautama.cabinbookingbackend.api.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long cabinId;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
}