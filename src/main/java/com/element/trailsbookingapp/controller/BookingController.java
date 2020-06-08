package com.element.trailsbookingapp.controller;


import com.element.trailsbookingapp.exception.BookingNotFoundException;
import com.element.trailsbookingapp.exception.InvalidBookingRequestException;
import com.element.trailsbookingapp.exception.TrailNotFoundException;
import com.element.trailsbookingapp.model.BookingDTO;
import com.element.trailsbookingapp.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/api/bookings")
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    @ExceptionHandler({TrailNotFoundException.class, InvalidBookingRequestException.class})
    public ResponseEntity<BookingDTO> save(@RequestBody BookingDTO bookingDTO) {
        log.info("Request to save booking {}", bookingDTO);
        return ResponseEntity.ok().body(bookingService.save(bookingDTO));
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookingsForGivenHikeDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Request to get all bookings for the day {}", date);
        return ResponseEntity.ok().body(bookingService.getAllBookingsForGivenHikeDate(date));
    }

    @DeleteMapping("/{id}")
    @ExceptionHandler({BookingNotFoundException.class})
    public ResponseEntity cancelBooking(@PathVariable("id") Integer id) {
        log.info("Request to cancel booking with id {}", id);
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }
}
