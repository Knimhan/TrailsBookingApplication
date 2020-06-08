package com.element.trailsbookingapp.integration;


import com.element.trailsbookingapp.entity.BookingEntity;
import com.element.trailsbookingapp.entity.HikerEntity;
import com.element.trailsbookingapp.entity.TrailEntity;
import com.element.trailsbookingapp.fixture.BookingFixture;
import com.element.trailsbookingapp.fixture.TrailFixture;
import com.element.trailsbookingapp.repository.BookingRepository;
import com.element.trailsbookingapp.repository.HikerRepository;
import com.element.trailsbookingapp.repository.TrailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HikerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrailRepository trailRepository;

    @Autowired
    private HikerRepository hikerRepository;

    @Test
    void testGetAllBookings() throws Exception {
        //given
        BookingEntity bookingEntity = givenBookingEntity();
        Integer hikerId = bookingEntity.getHikerEntities().get(0).getId();

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/hikers/" + hikerId + "/bookings"))
                .andExpect(status().isOk())
                .andReturn();
        //then
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    private BookingEntity givenBookingEntity() {
        TrailEntity trailEntity = trailRepository.save(TrailFixture.getTrailEntity());
        BookingEntity bookingEntity = BookingFixture.getBookingEntity();
        bookingEntity.setTrailEntity(trailEntity);
        bookingEntity = bookingRepository.save(bookingEntity);
        for (HikerEntity hikerEntity : bookingEntity.getHikerEntities()) {
            hikerEntity.setBookingEntity(bookingEntity);
            hikerRepository.save(hikerEntity);
        }
        return bookingEntity;
    }
}
