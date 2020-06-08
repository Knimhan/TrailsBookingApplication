package com.element.trailsbookingapp.service;

import com.element.trailsbookingapp.entity.TrailEntity;
import com.element.trailsbookingapp.model.TrailDTO;

import java.util.List;

public interface TrailService {
    TrailEntity get(Integer id);

    List<TrailDTO> getAll();

    void saveAll(List<TrailDTO> trailDTOList);
}
