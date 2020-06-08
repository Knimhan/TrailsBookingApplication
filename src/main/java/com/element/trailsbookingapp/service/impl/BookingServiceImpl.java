package com.element.trailsbookingapp.service.impl;


import com.element.trailsbookingapp.entity.BookingEntity;
import com.element.trailsbookingapp.entity.HikerEntity;
import com.element.trailsbookingapp.entity.TrailEntity;
import com.element.trailsbookingapp.exception.BookingNotFoundException;
import com.element.trailsbookingapp.exception.InvalidBookingRequestException;
import com.element.trailsbookingapp.mapper.BookingMapper;
import com.element.trailsbookingapp.model.BookingDTO;
import com.element.trailsbookingapp.model.HikerDTO;
import com.element.trailsbookingapp.repository.BookingRepository;
import com.element.trailsbookingapp.service.HikerService;
import com.element.trailsbookingapp.service.TrailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookingServiceImpl implements com.element.trailsbookingapp.service.BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrailService trailService;

    @Autowired
    private HikerService hikerService;

    @Override
    public List<BookingDTO> getAllBookingsForGivenHikeDate(LocalDate date) {
        return bookingRepository.findAllByHikeDate(date)
                .stream()
                .map(BookingMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO save(BookingDTO bookingDTO) {
        TrailEntity trailEntity = trailService.get(bookingDTO.getTrailId());
        validate(bookingDTO, trailEntity);
        return BookingMapper.map(saveBookingEntity(bookingDTO, trailEntity));
    }

    @Override
    public void cancelBooking(Integer id) {
        Optional<BookingEntity> optionalBookingEntity = bookingRepository.findById(id);
        if (!optionalBookingEntity.isPresent())
            throw new BookingNotFoundException(String.format("Booking with %d does not exist", id));
        log.info("Deleting booking with id {}", id);
        bookingRepository.delete(optionalBookingEntity.get());
    }

    private BookingEntity saveBookingEntity(BookingDTO bookingDTO, TrailEntity trailEntity) {
        BookingEntity bookingEntity = new BookingEntity();
        BeanUtils.copyProperties(bookingDTO, bookingEntity, "id");
        bookingEntity.setTrailEntity(trailEntity);
        bookingEntity.setHikerEntities(getHikerEntities(bookingDTO.getHikerDTOs()));
        log.info("Saving booking with id {}", bookingEntity.getId());
        bookingEntity = bookingRepository.save(bookingEntity);
        saveHikerEntity(bookingEntity);
        return bookingEntity;
    }

    private void saveHikerEntity(BookingEntity bookingEntity) {
        List<Integer> hikeIds = bookingEntity.getHikerEntities().stream().map(hikerEntity ->
                hikerEntity.getId()).collect(Collectors.toList());
        setBookingEntity(hikeIds, bookingEntity);
    }

    private void setBookingEntity(List<Integer> hikeIds, BookingEntity bookingEntity) {
        hikeIds.forEach(hikeId -> hikerService.setBookingEntity(hikeId, bookingEntity));
    }

    private List<HikerEntity> getHikerEntities(List<HikerDTO> hikerDTOs) {
        return hikerService.saveAll(hikerDTOs);
    }

    private void validate(BookingDTO bookingDTO, TrailEntity trailEntity) {
        validateIfHikeDateIsPresent(bookingDTO);
        validateIfHikersArePresent(bookingDTO);
        validateIfAllHikersAreInValidAgeRange(bookingDTO, trailEntity);
    }

    private void validateIfHikeDateIsPresent(BookingDTO bookingDTO) {
        if (bookingDTO.getHikeDate() == null) throw new InvalidBookingRequestException("Hike date is not valid");
    }

    private void validateIfHikersArePresent(BookingDTO bookingDTO) {
        if (bookingDTO.getHikerDTOs() == null || bookingDTO.getHikerDTOs().isEmpty())
            throw new InvalidBookingRequestException("Booking does not contain hikers");
    }

    private void validateIfAllHikersAreInValidAgeRange(BookingDTO bookingDTO, TrailEntity trailEntity) {
        if (bookingDTO.getHikerDTOs().stream()
                .anyMatch(hikerDTO -> {
                    if (isAgeInvalid(trailEntity, hikerDTO.getAge())) {
                        log.error("{} age {} is not valid for this trail", hikerDTO.getName(), hikerDTO.getAge());
                        return true;
                    }
                    return false;
                }))
            throw new
                    InvalidBookingRequestException(
                    "Could not create booking as age of hikers do not fall into required range");
    }

    private boolean isAgeInvalid(TrailEntity trailEntity, Integer age) {
        return (age > trailEntity.getMaxAgeLimit()) || (age < trailEntity.getMinAgeLimit());
    }
}
