package com.element.trailsbookingapp.mapper;

import com.element.trailsbookingapp.entity.BookingEntity;
import com.element.trailsbookingapp.model.BookingDTO;
import org.springframework.beans.BeanUtils;

public class BookingMapper {
    public static BookingDTO map(BookingEntity bookingEntity) {
        BookingDTO bookingDTO = new BookingDTO();
        BeanUtils.copyProperties(bookingEntity, bookingDTO);
        bookingDTO.setTrailId(bookingEntity.getTrailEntity().getId());
        bookingDTO.setHikerDTOs(HikerMapper.map(bookingEntity.getHikerEntities()));
        return bookingDTO;
    }
}
