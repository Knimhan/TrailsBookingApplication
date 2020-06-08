package com.element.trailsbookingapp.service;

import com.element.trailsbookingapp.exception.HikerNotFoundException;
import com.element.trailsbookingapp.repository.HikerRepository;
import com.element.trailsbookingapp.service.impl.HikerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HikerServiceImplTest {

    @Mock
    private HikerRepository hikerRepository;

    @InjectMocks
    private HikerServiceImpl hikerServiceImpl;

    @Test
    void testHikerNotFoundException() {
        //given
        Integer id = 100;
        when(hikerRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Throwable exception = assertThrows(HikerNotFoundException.class,
                () -> hikerServiceImpl.getBookingOfHiker(id));

        //then
        assertEquals(String.format("Hiker %d is not present", id),
                exception.getMessage());
    }
}
