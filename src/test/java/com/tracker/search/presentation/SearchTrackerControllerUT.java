package com.tracker.search.presentation;

import com.tracker.common.presentation.model.PetType;
import com.tracker.common.presentation.model.TrackerType;
import com.tracker.entity.EntityException;
import com.tracker.search.NoDataFoundException;
import com.tracker.search.SearchTrackerException;
import com.tracker.search.presentation.model.PetTrackerSearchReqModel;
import com.tracker.search.presentation.model.TrackerModel;
import com.tracker.search.presentation.model.TrackerSearchResModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchTrackerControllerUT {

    @Mock
    private SearchTrackerService searchTrackerService;

    @InjectMocks
    private SearchTrackerController controller;

    @Test
    void testSearchByTrackerId() throws EntityException, NoDataFoundException, SearchTrackerException {
        TrackerModel trackerModel = new TrackerModel();
        trackerModel.setTrackerId("1");
        trackerModel.setPetType("DOG");
        trackerModel.setInZone(true);
        trackerModel.setTrackerType("SMALL");
        when(searchTrackerService.searchByTrackerId(1L)).thenReturn(trackerModel);
        TrackerModel model = controller.searchTracker(1L);
        assertNotNull(model);
    }

    @Test
    void testSearchByPetTypeTrackerType() throws EntityException, NoDataFoundException, SearchTrackerException {
        PetTrackerSearchReqModel reqModel = new PetTrackerSearchReqModel();
        reqModel.setPetType(PetType.CAT);
        reqModel.setTrackerType(TrackerType.BIG);
        TrackerSearchResModel resModel = new TrackerSearchResModel();
        when(searchTrackerService.searchByPetTypeTrackerType(PetType.CAT, TrackerType.BIG)).thenReturn(resModel);
        TrackerSearchResModel resModelFromController = controller.searchByPetAndTrackerType(reqModel);
        assertNotNull(resModelFromController);
        verify(searchTrackerService).searchByPetTypeTrackerType(PetType.CAT, TrackerType.BIG);
    }

    @Test
    void testCountForOutSidePowerZone() throws EntityException, NoDataFoundException {
        PetTrackerSearchReqModel reqModel = new PetTrackerSearchReqModel();
        reqModel.setPetType(PetType.CAT);
        reqModel.setTrackerType(TrackerType.BIG);
        TrackerSearchResModel resModel = new TrackerSearchResModel();
        when(searchTrackerService.getPetsOutSideOfZone(PetType.CAT, TrackerType.BIG)).thenReturn(5L);
        Long count = controller.numberOfPetsOutsidePowerZone(reqModel);
        assertEquals(5L, count);
    }
}
