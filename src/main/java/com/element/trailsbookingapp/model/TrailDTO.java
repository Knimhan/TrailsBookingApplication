package com.element.trailsbookingapp.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class TrailDTO {
    private Integer id;

    private String name;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer minAgeLimit;

    private Integer maxAgeLimit;

    private BigDecimal price;
}
