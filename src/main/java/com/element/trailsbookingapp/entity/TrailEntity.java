package com.element.trailsbookingapp.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "trail")
public class TrailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer minAgeLimit;

    private Integer maxAgeLimit;

    private BigDecimal price;
}
