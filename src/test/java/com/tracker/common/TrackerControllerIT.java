package com.tracker.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracker.cat.CatConstants;
import com.tracker.cat.infrastructure.CatTrackerRepository;
import com.tracker.cat.presentation.model.CatTrackerType;
import com.tracker.common.presentation.model.TrackerResModel;
import com.tracker.entity.EntityException;
import com.tracker.entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class TrackerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Order(1)
    @Test
    void updateZoneTrackerStatus_Success() throws Exception {
        TrackerResModel responseModel = new TrackerResModel("Zone status updated successfully");
        String responseJson = objectMapper.writeValueAsString(responseModel);
        Long trackerId = repository.findAll().iterator().next().getTracker().getId();
        mockMvc.perform(MockMvcRequestBuilders.patch("/tracker/pet/zone")
                        .param("trackerId", "" + trackerId)
                        .param("inZone", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Order(2)
    @Test
    void updateZoneTrackerStatus_NoDataFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/tracker/pet/zone")
                        .param("trackerId", "999")
                        .param("inZone", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
