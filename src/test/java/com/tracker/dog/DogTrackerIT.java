package com.tracker.dog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tracker.common.presentation.model.PetTrackerResponseModel;
import com.tracker.dog.presentation.model.DogTrackerReqModel;
import com.tracker.dog.presentation.model.DogTrackerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DogTrackerIT {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void registerDogTrackerType_Big_Success() throws Exception {
        DogTrackerReqModel reqModel = new DogTrackerReqModel(DogTrackerType.BIG,
                1234);
        String responseString = mockMvc.perform(MockMvcRequestBuilders
                        .post("/tracker/dog/register")
                        .content(asJsonString(reqModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        PetTrackerResponseModel responseModel =
                mapper.readValue(responseString, PetTrackerResponseModel.class);
        assertNotNull(responseModel.getTrackerId());
    }

    @Test
    void registerDogTrackerType_MEDIUM_Success() throws Exception {
        DogTrackerReqModel reqModel = new DogTrackerReqModel(DogTrackerType.MEDIUM,
                1234);
        String responseString = mockMvc.perform(MockMvcRequestBuilders
                        .post("/tracker/dog/register")
                        .content(asJsonString(reqModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        PetTrackerResponseModel responseModel =
                mapper.readValue(responseString, PetTrackerResponseModel.class);
        assertNotNull(responseModel.getTrackerId());
    }

    @Test
    void registerDogTrackerType_SMALL_Success() throws Exception {
        DogTrackerReqModel reqModel = new DogTrackerReqModel(DogTrackerType.SMALL,
                1234);
        String responseString = mockMvc.perform(MockMvcRequestBuilders
                        .post("/tracker/dog/register")
                        .content(asJsonString(reqModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        PetTrackerResponseModel responseModel =
                mapper.readValue(responseString, PetTrackerResponseModel.class);
        assertNotNull(responseModel.getTrackerId());
    }

    @Test
    void registerDogTrackerType_InvalidOwnerId_Fail() throws Exception {
        DogTrackerReqModel reqModel = new DogTrackerReqModel(DogTrackerType.SMALL,
                null);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/tracker/dog/register")
                        .content(asJsonString(reqModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }

}
