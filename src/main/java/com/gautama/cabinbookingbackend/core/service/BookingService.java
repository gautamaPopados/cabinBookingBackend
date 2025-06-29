package com.gautama.cabinbookingbackend.core.service;

import com.gautama.cabinbookingbackend.api.dto.BookingDto;
import com.gautama.cabinbookingbackend.api.dto.BookingRequestDto;
import com.gautama.cabinbookingbackend.api.enums.BookingStatus;
import com.gautama.cabinbookingbackend.api.enums.Role;
import com.gautama.cabinbookingbackend.core.model.Booking;
import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.model.User;
import com.gautama.cabinbookingbackend.core.repository.BookingRepository;
import com.gautama.cabinbookingbackend.core.repository.CabinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final CabinRepository cabinRepository;
    private final BookingRepository bookingRepository;

    public Booking createBooking(BookingRequestDto dto, User user) {
        Cabin cabin = cabinRepository.findById(dto.getId()).orElseThrow(() -> new NoSuchElementException("No such cabin"));

        Booking booking = new Booking();
        booking.setCabinId(dto.getId());
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setUserId(user.getId());
        booking.setStatus(user.getRole().equals(Role.ADMIN) ? BookingStatus.APPROVED : BookingStatus.PENDING);
        booking.setCabinName(cabin.getName());
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void updateStatus(Long id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByCabinId(Long cabinId) {
        LocalDate today = LocalDate.now();
        return bookingRepository.findAllByCabinIdAndEndDateAfter(cabinId, today);
    }

    public List<BookingDto> getBookingDtosByUser(User user) {
        List<Booking> bookings = bookingRepository.findAllByUserId(user.getId());

        return bookings.stream().map(booking -> new BookingDto(
                booking.getCabinId(),
                booking.getCabinName(),
                user.getFirstName() + user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getStatus()
        )).toList();
    }
}
