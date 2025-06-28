package com.gautama.cabinbookingbackend.core.repository;

import com.gautama.cabinbookingbackend.core.model.Booking;
import com.gautama.cabinbookingbackend.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByCabinIdAndEndDateAfter(Long cabinId, LocalDate date);
    List<Booking> findAllByUserId(Long userId);

}