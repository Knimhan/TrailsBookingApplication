package com.element.trailsbookingapp.service;

import com.element.trailsbookingapp.entity.TrailEntity;
import com.element.trailsbookingapp.exception.TrailNotFoundException;
import com.element.trailsbookingapp.model.TrailDTO;
import com.element.trailsbookingapp.repository.TrailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrailService {

    @Autowired
    private TrailRepository trailRepository;

    public TrailEntity get(Integer id) {
        Optional<TrailEntity> optionalTrailEntity = trailRepository.findById(id);
        if (!optionalTrailEntity.isPresent())
            throw new TrailNotFoundException(String.format("Trail with id %d not found", id));
        return optionalTrailEntity.get();
    }

    public List<TrailDTO> getAll() {
        List<TrailDTO> trailDTOS = new ArrayList<>();
        for (TrailEntity trailEntity : trailRepository.findAll()) {
            TrailDTO trailDTO = new TrailDTO();
            BeanUtils.copyProperties(trailEntity, trailDTO);
            trailDTOS.add(trailDTO);
        }
        return trailDTOS;
    }

    public void saveAll(List<TrailDTO> trailDTOList) {
        trailDTOList.forEach(trail -> save(trail));
    }

    private TrailDTO save(TrailDTO trailDTO) {
        TrailEntity trailEntity = new TrailEntity();
        BeanUtils.copyProperties(trailDTO, trailEntity, "id");
        trailEntity = trailRepository.save(trailEntity);
        BeanUtils.copyProperties(trailEntity, trailDTO);
        return trailDTO;
    }
}
