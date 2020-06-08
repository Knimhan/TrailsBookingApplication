package com.element.trailsbookingapp.controller;


import com.element.trailsbookingapp.model.TrailDTO;
import com.element.trailsbookingapp.service.TrailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/trails")
@RestController
public class TrailController {

    @Autowired
    private TrailService trailService;

    @GetMapping
    public ResponseEntity<List<TrailDTO>> getAll() {
        log.info("Request to get all trails");
        return ResponseEntity.ok().body(trailService.getAll());
    }
}
