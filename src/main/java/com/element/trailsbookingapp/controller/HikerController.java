package com.element.trailsbookingapp.controller;


import com.element.trailsbookingapp.exception.HikerNotFoundException;
import com.element.trailsbookingapp.exception.InvalidHikerCreationException;
import com.element.trailsbookingapp.model.BookingDTO;
import com.element.trailsbookingapp.service.HikerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/hikers")
@RestController
public class HikerController {

    @Autowired
    private HikerService hikerService;

    @GetMapping("/{id}/bookings")
    @ExceptionHandler({HikerNotFoundException.class, InvalidHikerCreationException.class})
    public ResponseEntity<BookingDTO> viewBookingOfHiker(@PathVariable("id") Integer id) {
        log.info("Request to get all hikers");
        return ResponseEntity.ok().body(hikerService.getBookingOfHiker(id));
    }
}
