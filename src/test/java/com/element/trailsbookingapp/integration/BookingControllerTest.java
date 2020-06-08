package com.element.trailsbookingapp.integration;


import com.element.trailsbookingapp.entity.BookingEntity;
import com.element.trailsbookingapp.entity.TrailEntity;
import com.element.trailsbookingapp.fixture.BookingFixture;
import com.element.trailsbookingapp.fixture.TrailFixture;
import com.element.trailsbookingapp.model.BookingDTO;
import com.element.trailsbookingapp.repository.BookingRepository;
import com.element.trailsbookingapp.repository.TrailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrailRepository trailRepository;


    @Test
    void testGetAllBookings() throws Exception {
        //given
        givenBookingEntity();

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/bookings?date=2020-06-06"))
                .andExpect(status().isOk())
                .andReturn();
        //then
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse().getContentAsString());
        assertEquals("2020-06-06", getBookingDTOS(mvcResult).get(0).getHikeDate().format(DateTimeFormatter.ISO_DATE));
    }

    @Test
    void testCreateBooking() throws Exception {
        //given
        BookingDTO bookingDTO = BookingFixture.getBookingDTO();
        String json = objectMapper.writeValueAsString(bookingDTO);

        //when
        MvcResult mvcResult = mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        //then
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testCancelBooking() throws Exception {
        //given
        givenBookingEntity();


        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/bookings/1"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    private ArrayList<BookingDTO> getBookingDTOS(MvcResult mvcResult) throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        BookingDTO[] pojos = mapper.readValue(
                mvcResult.getResponse().getContentAsByteArray(),
                BookingDTO[].class);
        return new ArrayList<>(Arrays.asList(pojos));
    }

    private void givenBookingEntity() {
        TrailEntity trailEntity = trailRepository.save(TrailFixture.getTrailEntity());
        BookingEntity bookingEntity = BookingFixture.getBookingEntity();
        bookingEntity.setTrailEntity(trailEntity);
        bookingRepository.save(bookingEntity);
    }
}
