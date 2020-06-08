package com.element.trailsbookingapp.service;

import com.element.trailsbookingapp.exception.HikerNotFoundException;
import com.element.trailsbookingapp.repository.HikerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HikerServiceTest {

    @Mock
    private HikerRepository hikerRepository;

    @InjectMocks
    private HikerService hikerService;

    @Test
    void testHikerNotFoundException() {
        //given
        Integer id = 100;
        when(hikerRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Throwable exception = assertThrows(HikerNotFoundException.class,
                () -> hikerService.getBookingOfHiker(id));

        //then
        assertEquals(String.format("Hiker %d is not present", id),
                exception.getMessage());
    }
}
