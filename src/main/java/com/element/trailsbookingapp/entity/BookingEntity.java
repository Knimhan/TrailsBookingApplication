package com.element.trailsbookingapp.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate hikeDate;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<HikerEntity> hikerEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trail_id", referencedColumnName = "id", nullable = false)
    private TrailEntity trailEntity;
}
