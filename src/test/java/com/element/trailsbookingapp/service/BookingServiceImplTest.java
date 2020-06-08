package com.element.trailsbookingapp.service;

import com.element.trailsbookingapp.exception.BookingNotFoundException;
import com.element.trailsbookingapp.exception.InvalidBookingRequestException;
import com.element.trailsbookingapp.fixture.BookingFixture;
import com.element.trailsbookingapp.fixture.TrailFixture;
import com.element.trailsbookingapp.model.BookingDTO;
import com.element.trailsbookingapp.repository.BookingRepository;
import com.element.trailsbookingapp.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private TrailService trailService;

    @InjectMocks
    private BookingServiceImpl bookingServiceImpl;

    @Test
    void testInvalidBookingRequestExceptionIsThrownWhenAgeIsInvalid() {
        //given
        BookingDTO bookingDTO = BookingFixture.getInvalidBookingDTO();
        when(trailService.get(1)).thenReturn(TrailFixture.getTrailEntity());

        //when
        Throwable exception = assertThrows(InvalidBookingRequestException.class,
                () -> bookingServiceImpl.save(bookingDTO));

        //then
        assertEquals("Could not create booking as age of hikers do not fall into required range",
                exception.getMessage());

    }

    @Test
    void testInvalidBookingRequestExceptionIfHikerDtoIsEmpty() {
        //given
        BookingDTO bookingDTO = BookingFixture.getBookingDTOWithEmptyHikers();
        when(trailService.get(1)).thenReturn(TrailFixture.getTrailEntity());

        //when
        Throwable exception = assertThrows(InvalidBookingRequestException.class,
                () -> bookingServiceImpl.save(bookingDTO));

        //then
        assertEquals("Booking does not contain hikers",
                exception.getMessage());
    }

    @Test
    void testInvalidBookingRequestExceptionIfHikerDateIsNull() {
        //given
        BookingDTO bookingDTO = BookingFixture.getBookingDTOWithEmptyHikers();
        bookingDTO.setHikeDate(null);
        when(trailService.get(1)).thenReturn(TrailFixture.getTrailEntity());

        //when
        Throwable exception = assertThrows(InvalidBookingRequestException.class,
                () -> bookingServiceImpl.save(bookingDTO));

        //then
        assertEquals("Hike date is not valid",
                exception.getMessage());
    }

    @Test
    void testBookingNotFoundExceptionIsThrownWhenCancelBookingIsCalled() {
        //given
        Integer id = 100;
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Throwable exception = assertThrows(BookingNotFoundException.class,
                () -> bookingServiceImpl.cancelBooking(id));

        //then
        assertEquals(String.format("Booking with %d does not exist", id),
                exception.getMessage());
    }
}
