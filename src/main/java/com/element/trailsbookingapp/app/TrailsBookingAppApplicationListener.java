package com.element.trailsbookingapp.app;

import com.element.trailsbookingapp.model.TrailDTO;
import com.element.trailsbookingapp.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrailsBookingAppApplicationListener
        implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TrailService trailService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        trailService.saveAll(getTrails());
    }

    private List<TrailDTO> getTrails() {
        List<TrailDTO> trailDTOS = new ArrayList<>();
        trailDTOS.add(getTrail("Shire", LocalTime.of(7, 00), LocalTime.of(9, 00), 5, 100, new BigDecimal("29.90")));
        trailDTOS.add(getTrail("Gondor", LocalTime.of(10, 00), LocalTime.of(13, 00), 11, 50, new BigDecimal("59.90")));
        trailDTOS.add(getTrail("Mordor", LocalTime.of(14, 00), LocalTime.of(19, 00), 18, 40, new BigDecimal("99.90")));
        return trailDTOS;
    }

    private TrailDTO getTrail(String name,
                              LocalTime startTime, LocalTime endtime,
                              Integer minAgeLimit, Integer maxAgeLimit,
                              BigDecimal price) {
        TrailDTO trailDTO = new TrailDTO();
        trailDTO.setName(name);
        trailDTO.setStartTime(startTime);
        trailDTO.setEndTime(endtime);
        trailDTO.setMinAgeLimit(minAgeLimit);
        trailDTO.setMaxAgeLimit(maxAgeLimit);
        trailDTO.setPrice(price);
        return trailDTO;
    }
}
