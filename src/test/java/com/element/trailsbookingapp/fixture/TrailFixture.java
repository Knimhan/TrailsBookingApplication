package com.element.trailsbookingapp.fixture;

import com.element.trailsbookingapp.entity.TrailEntity;

import java.math.BigDecimal;
import java.time.LocalTime;

public class TrailFixture {

    public static TrailEntity getTrailEntity() {
        TrailEntity trailEntity = new TrailEntity();
        //trailEntity.setId(1);
        trailEntity.setName("Shire");
        trailEntity.setStartTime(LocalTime.of(7, 00));
        trailEntity.setEndTime(LocalTime.of(9, 00));
        trailEntity.setMinAgeLimit(5);
        trailEntity.setMaxAgeLimit(100);
        trailEntity.setPrice(new BigDecimal("29.90"));
        return trailEntity;
    }
}
