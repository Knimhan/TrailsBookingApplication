package com.element.trailsbookingapp.service;

import com.element.trailsbookingapp.exception.TrailNotFoundException;
import com.element.trailsbookingapp.repository.TrailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrailServiceTest {

    @Mock
    TrailRepository trailRepository;

    @InjectMocks
    private TrailService trailService;


    @Test
    void testTrailNotFoundExceptionIsThrown() {
        //given
        Integer id = 100;
        when(trailRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Throwable exception = assertThrows(TrailNotFoundException.class,
                () -> trailService.get(id));

        //then
        assertEquals(String.format("Trail with id %d not found", id),
                exception.getMessage());
    }
}
