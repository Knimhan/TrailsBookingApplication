package com.element.trailsbookingapp.service.impl;


import com.element.trailsbookingapp.entity.BookingEntity;
import com.element.trailsbookingapp.entity.HikerEntity;
import com.element.trailsbookingapp.exception.HikerNotFoundException;
import com.element.trailsbookingapp.exception.InvalidHikerCreationException;
import com.element.trailsbookingapp.mapper.BookingMapper;
import com.element.trailsbookingapp.mapper.HikerMapper;
import com.element.trailsbookingapp.model.BookingDTO;
import com.element.trailsbookingapp.model.HikerDTO;
import com.element.trailsbookingapp.repository.HikerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HikerServiceImpl implements com.element.trailsbookingapp.service.HikerService {

    @Autowired
    private HikerRepository hikerRepository;

    @Override
    public List<HikerEntity> saveAll(List<HikerDTO> hikerDTOS) {
        return hikerDTOS
                .stream()
                .map(hikerDTO -> {
                    log.info("Saving hiker {}", hikerDTO.getName());
                    return hikerRepository.save(HikerMapper.map(hikerDTO));
                })
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingOfHiker(Integer id) {
        return BookingMapper.map(getBookingEntity(getHikerEntity(id)));
    }

    @Override
    public void setBookingEntity(Integer id, BookingEntity bookingEntity) {
        HikerEntity hikerEntity = getHikerEntity(id);
        hikerEntity.setBookingEntity(bookingEntity);
        log.info("Saving hiker entity with id {}", id);
        hikerRepository.save(hikerEntity);
    }

    private BookingEntity getBookingEntity(HikerEntity hikerEntity) {
        BookingEntity bookingEntity = hikerEntity.getBookingEntity();
        if (bookingEntity == null)
            throw new InvalidHikerCreationException("Hiker can not be present without booking associated with it");
        return bookingEntity;
    }

    private HikerEntity getHikerEntity(Integer id) {
        Optional<HikerEntity> optionalHikerEntity = hikerRepository.findById(id);
        if (!optionalHikerEntity.isPresent())
            throw new HikerNotFoundException(String.format("Hiker %d is not present", id));
        return optionalHikerEntity.get();
    }
}
