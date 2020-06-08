package com.element.trailsbookingapp.repository;

import com.element.trailsbookingapp.entity.TrailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailRepository extends JpaRepository<TrailEntity, Integer> {
}
