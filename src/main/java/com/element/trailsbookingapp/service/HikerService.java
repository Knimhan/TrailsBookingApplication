package com.element.trailsbookingapp.service;

import com.element.trailsbookingapp.entity.BookingEntity;
import com.element.trailsbookingapp.entity.HikerEntity;
import com.element.trailsbookingapp.model.BookingDTO;
import com.element.trailsbookingapp.model.HikerDTO;

import java.util.List;

public interface HikerService {
    List<HikerEntity> saveAll(List<HikerDTO> hikerDTOS);

    BookingDTO getBookingOfHiker(Integer id);

    void setBookingEntity(Integer id, BookingEntity bookingEntity);
}
