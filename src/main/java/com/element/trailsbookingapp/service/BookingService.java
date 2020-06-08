package com.element.trailsbookingapp.service;

import com.element.trailsbookingapp.model.BookingDTO;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    List<BookingDTO> getAllBookingsForGivenHikeDate(LocalDate date);

    BookingDTO save(BookingDTO bookingDTO);

    void cancelBooking(Integer id);
}
