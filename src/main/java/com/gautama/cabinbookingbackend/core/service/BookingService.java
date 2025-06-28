package com.gautama.cabinbookingbackend.core.service;

import com.gautama.cabinbookingbackend.api.dto.BookingDto;
import com.gautama.cabinbookingbackend.api.dto.BookingRequestDto;
import com.gautama.cabinbookingbackend.api.enums.BookingStatus;
import com.gautama.cabinbookingbackend.core.model.Booking;
import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.model.User;
import com.gautama.cabinbookingbackend.core.repository.BookingRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(BookingRequestDto dto) {
        Booking booking = new Booking();
        booking.setCabinId(dto.getCabinId());
        booking.setStartDate(dto.getStartDate());
        booking.setEndDate(dto.getEndDate());
        booking.setStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void updateStatus(Long id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByCabinId(Long cabinId) {
        LocalDate today = LocalDate.now();
        return bookingRepository.findAllByCabinIdAndEndDateAfter(cabinId, today);
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findAllByUser(user);
    }

    public List<BookingDto> getBookingDtosByUser(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException("Токен отсутствует или не верен");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Пользователь не найден"));

        List<Booking> bookings = bookingService.getBookingsByUser(user);

        List<BookingDto> bookingDtos = bookings.stream().map(booking -> {
            Cabin cabin = booking.getCabin();
            return new BookingDto(
                    cabin.getId(),
                    cabin.getName(),
                    booking.getStartDate(),
                    booking.getEndDate(),
                    booking.getStatus()
            );
        }).toList();

        return bookingDtos;
    }
}

