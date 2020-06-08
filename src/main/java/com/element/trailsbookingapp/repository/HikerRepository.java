package com.element.trailsbookingapp.repository;

import com.element.trailsbookingapp.entity.HikerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HikerRepository extends JpaRepository<HikerEntity, Integer> {
}
