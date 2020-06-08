package com.element.trailsbookingapp.repository;

import com.element.trailsbookingapp.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    List<BookingEntity> findAllByHikeDate(LocalDate date);
}
