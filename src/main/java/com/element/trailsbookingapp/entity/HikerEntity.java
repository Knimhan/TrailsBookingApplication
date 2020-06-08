package com.element.trailsbookingapp.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "hiker")
public class HikerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private BookingEntity bookingEntity;

}
