package com.element.trailsbookingapp.mapper;

import com.element.trailsbookingapp.entity.HikerEntity;
import com.element.trailsbookingapp.model.HikerDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class HikerMapper {

    public static List<HikerDTO> map(List<HikerEntity> hikerEntities) {
        return hikerEntities.stream()
                .map(HikerMapper::map)
                .collect(Collectors.toList());
    }

    public static HikerEntity map(HikerDTO hikerDTO) {
        HikerEntity hikerEntity = new HikerEntity();
        BeanUtils.copyProperties(hikerDTO, hikerEntity, "id");
        return hikerEntity;
    }

    public static HikerDTO map(HikerEntity hikerEntity) {
        HikerDTO hikerDTO = new HikerDTO();
        BeanUtils.copyProperties(hikerEntity, hikerDTO);
        return hikerDTO;
    }
}
