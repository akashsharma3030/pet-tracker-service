package com.tracker.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tracker.cat.CatConstants;
import com.tracker.cat.infrastructure.CatTrackerRepository;
import com.tracker.cat.presentation.model.CatTrackerType;
import com.tracker.entity.EntityException;
import com.tracker.entity.Pet;
import com.tracker.search.presentation.model.CatTrackerModel;
import com.tracker.search.presentation.model.TrackerSearchRespModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchTrackerIT {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CatTrackerRepository repository;
    private boolean dataSetupFlag = false;

    @BeforeEach
    public void setUpData() throws EntityException {
        if (!dataSetupFlag) {
            dataSetupFlag = true;
            savePetCat(2);
        }
    }

    private void savePetCat(int count) throws EntityException {
        List<Pet> petList = new ArrayList<>();
        for (int index = 1; index <= count; index++) {
            Pet cat = new Pet(1, CatConstants.PET_TYPE_CAT, CatTrackerType.BIG.name(), true);
            petList.add(cat);
            repository.save(cat);
        }

    }

    @Test
    void testSearchTrackerById_Success() throws Exception {
        Pet pet = repository.findAll().iterator().next();
        String responseString = mockMvc.perform(MockMvcRequestBuilders
                        .get("/search/id?trackerId=" + pet.getTracker().getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        TrackerSearchRespModel responseModel =
                mapper.readValue(responseString, TrackerSearchRespModel.class);
        assertEquals(String.valueOf(pet.getTracker().getId()), responseModel.getCatTrackerModel().getTrackerId());
    }


}
