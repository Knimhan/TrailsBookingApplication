package com.element.trailsbookingapp.fixture;


import com.element.trailsbookingapp.entity.HikerEntity;
import com.element.trailsbookingapp.model.HikerDTO;

import java.util.Arrays;
import java.util.List;

public class HikerFixture {

    public static final int AGE = 22;
    public static final String NAME = "kumudini";

    public static HikerEntity getHikerEntity() {
        HikerEntity hikerEntity = new HikerEntity();
        hikerEntity.setAge(AGE);
        hikerEntity.setName(NAME);
        return hikerEntity;
    }

    public static List<HikerDTO> getHikerDTOs() {
        HikerDTO hikerDTO = new HikerDTO();
        hikerDTO.setAge(AGE);
        hikerDTO.setName(NAME);
        return Arrays.asList(hikerDTO);
    }

    public static List<HikerDTO> getInvalidHikerDTOs() {
        HikerDTO hikerDTO = new HikerDTO();
        hikerDTO.setAge(1);
        hikerDTO.setName(NAME);
        return Arrays.asList(hikerDTO);
    }
}
