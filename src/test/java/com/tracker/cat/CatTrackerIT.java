package com.tracker.cat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tracker.cat.infrastructure.CatTrackerRepository;
import com.tracker.cat.presentation.model.CatLostTrackerReqModel;
import com.tracker.cat.presentation.model.CatLostTrackerResModel;
import com.tracker.cat.presentation.model.CatTrackerReqModel;
import com.tracker.cat.presentation.model.CatTrackerType;
import com.tracker.common.presentation.model.PetTrackerResponseModel;
import com.tracker.entity.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CatTrackerIT {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CatTrackerRepository repository;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void registerCatTrackerType_Big_Success() throws Exception {
        CatTrackerReqModel reqModel = new CatTrackerReqModel(CatTrackerType.BIG,
                1234);
        String responseString = mockMvc.perform(MockMvcRequestBuilders
                        .post("/tracker/cat/register")
                        .content(asJsonString(reqModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        PetTrackerResponseModel responseModel =
                mapper.readValue(responseString, PetTrackerResponseModel.class);
        assertNotNull(responseModel.getTrackerId());
    }

    @Test
    void registerCatTrackerType_SMALL_Success() throws Exception {
        CatTrackerReqModel reqModel = new CatTrackerReqModel(CatTrackerType.SMALL,
                1234);
        String responseString = mockMvc.perform(MockMvcRequestBuilders
                        .post("/tracker/cat/register")
                        .content(asJsonString(reqModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        PetTrackerResponseModel responseModel =
                mapper.readValue(responseString, PetTrackerResponseModel.class);
        assertNotNull(responseModel.getTrackerId());
    }

    @Test
    void registerCatTrackerType_InvalidOwnerId_Fail() throws Exception {
        CatTrackerReqModel reqModel = new CatTrackerReqModel(CatTrackerType.SMALL, null);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/tracker/cat/register")
                        .content(asJsonString(reqModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void lostTracker_Success() throws Exception {
        Pet cat = new Pet(1, CatConstants.PET_TYPE_CAT, CatTrackerType.BIG.name(), true);
        Pet savedPet = repository.save(cat);
        CatLostTrackerReqModel lostTrackerReqModel = new CatLostTrackerReqModel(savedPet.getTracker().getId(),
                true);

        String lostTrackerResponse = mockMvc.perform(MockMvcRequestBuilders
                        .patch("/tracker/cat/lost")
                        .content(asJsonString(lostTrackerReqModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        CatLostTrackerResModel catLostTrackerResModel =
                mapper.readValue(lostTrackerResponse, CatLostTrackerResModel.class);
        assertNotNull(catLostTrackerResModel);

        Pet updatedCat = repository.findByTracker_Id(savedPet.getTracker().getId()).get();
        assertTrue(updatedCat.getTracker().isLostTracker());
    }
}
