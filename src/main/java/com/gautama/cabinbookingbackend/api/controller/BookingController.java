package com.gautama.cabinbookingbackend.api.controller;

import com.gautama.cabinbookingbackend.api.config.JwtUtil;
import com.gautama.cabinbookingbackend.api.dto.BookingDto;
import com.gautama.cabinbookingbackend.api.dto.BookingRequestDto;
import com.gautama.cabinbookingbackend.api.enums.BookingStatus;
import com.gautama.cabinbookingbackend.core.model.Booking;
import com.gautama.cabinbookingbackend.core.model.Cabin;
import com.gautama.cabinbookingbackend.core.model.User;
import com.gautama.cabinbookingbackend.core.repository.UserRepository;
import com.gautama.cabinbookingbackend.core.service.BookingService;
import com.gautama.cabinbookingbackend.core.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequestDto bookingRequestDto, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException("Токен отсутствует или не верен");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Пользователь не найден"));

        Booking booking = bookingService.createBooking(bookingRequestDto, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @GetMapping("/admin")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approveBooking(@PathVariable("id") Long id) {
        bookingService.updateStatus(id, BookingStatus.APPROVED);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> rejectBooking(@PathVariable("id") Long id) {
        bookingService.updateStatus(id, BookingStatus.REJECTED);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cabin/{id}")
    public ResponseEntity<List<Booking>> getBookingsByCabin(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByCabinId(id));
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingDto>> getMyBookings(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException("Токен отсутствует или не верен");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Пользователь не найден"));

        List<BookingDto> bookingDtos = bookingService.getBookingDtosByUser(user);
        return ResponseEntity.ok(bookingDtos);
    }
}
