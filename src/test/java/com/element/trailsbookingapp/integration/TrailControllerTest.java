package com.element.trailsbookingapp.integration;


import com.element.trailsbookingapp.fixture.TrailFixture;
import com.element.trailsbookingapp.model.TrailDTO;
import com.element.trailsbookingapp.repository.TrailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrailRepository trailRepository;

    @Test
    void testGetAllBookings() throws Exception {
        //given
        trailRepository.save(TrailFixture.getTrailEntity());

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/trails"))
                .andExpect(status().isOk())
                .andReturn();
        //then
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse().getContentAsString());
        assertEquals("Shire", getTrailDTOS(mvcResult).get(0).getName());
    }

    private ArrayList<TrailDTO> getTrailDTOS(MvcResult mvcResult) throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        TrailDTO[] pojos = mapper.readValue(
                mvcResult.getResponse().getContentAsByteArray(),
                TrailDTO[].class);
        return new ArrayList<>(Arrays.asList(pojos));
    }
}
