package com.element.trailsbookingapp.fixture;

import com.element.trailsbookingapp.entity.BookingEntity;
import com.element.trailsbookingapp.model.BookingDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static com.element.trailsbookingapp.fixture.TrailFixture.getTrailEntity;

public class BookingFixture {

    public static BookingEntity getBookingEntity() {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setHikeDate(LocalDate.of(2020, 06, 06));
        bookingEntity.setTrailEntity(getTrailEntity());
        bookingEntity.setHikerEntities(Arrays.asList(HikerFixture.getHikerEntity()));
        return bookingEntity;
    }

    public static BookingDTO getBookingDTO() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setTrailId(1);
        bookingDTO.setHikeDate(LocalDate.of(2020, 06, 06));
        bookingDTO.setHikerDTOs(HikerFixture.getHikerDTOs());
        return bookingDTO;
    }

    public static BookingDTO getInvalidBookingDTO() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setTrailId(1);
        bookingDTO.setHikeDate(LocalDate.of(2020, 06, 06));
        bookingDTO.setHikerDTOs(HikerFixture.getInvalidHikerDTOs());
        return bookingDTO;
    }

    public static BookingDTO getBookingDTOWithEmptyHikers() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setTrailId(1);
        bookingDTO.setHikeDate(LocalDate.of(2020, 06, 06));
        bookingDTO.setHikerDTOs(new ArrayList<>());
        return bookingDTO;
    }
}
