package com.tracker.cat.presentation;

import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.presentation.model.*;
import com.tracker.common.presentation.model.PetTrackerResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatTrackerControllerUT {

    @Mock
    private CatTrackerService catTrackerService;

    @InjectMocks
    private CatTrackerController catTrackerController;

    @Test
    void registerCatTracker_Success() throws CatTrackerInternalException, CatTrackerException {
        // Arrange
        CatTrackerReqModel reqModel = new CatTrackerReqModel(CatTrackerType.BIG,
                12345);

        PetTrackerResponseModel expectedResponse = new PetTrackerResponseModel(123L,
                "Cat Tracker created");

        when(catTrackerService.createCatTracker(any(CatTrackerModel.class)))
                .thenReturn(expectedResponse);

        // Act
        PetTrackerResponseModel actualResponse = catTrackerController.registerCatTracker(reqModel);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

    }


    @Test
    void registerCatTracker_ThrowsException() throws CatTrackerException, CatTrackerInternalException {
        // Arrange
        CatTrackerReqModel reqModel = new CatTrackerReqModel(CatTrackerType.BIG,
                12345);
        CatTrackerException trackerException = new CatTrackerException("Exception");
        when(catTrackerService.createCatTracker(any(CatTrackerModel.class)))
                .thenThrow(trackerException);
        // Act
        assertThrows(CatTrackerException.class, () -> {
            catTrackerController.registerCatTracker(reqModel);
        });
    }


    @Test
    void lostCatTracker_Success() throws CatTrackerException {

        CatLostTrackerReqModel reqModel = new CatLostTrackerReqModel(1L, true);
        CatLostTrackerResModel resModel = new CatLostTrackerResModel("Lost Tracker Updated");
        when(catTrackerService.updateLostTracker(reqModel)).thenReturn(resModel);
        CatLostTrackerResModel response = catTrackerController.lostTracker(reqModel);
        assertNotNull(response);
        verify(catTrackerService).updateLostTracker(reqModel);

    }

    @Test
    void lostCatTracker_ThrowException() throws CatTrackerException {

        CatLostTrackerReqModel reqModel = new CatLostTrackerReqModel(1L, true);
        CatTrackerException trackerException = new CatTrackerException("Exception");
        when(catTrackerService.updateLostTracker(reqModel)).thenThrow(trackerException);
        assertThrows(CatTrackerException.class, () -> {
            catTrackerController.lostTracker(reqModel);
        });
        verify(catTrackerService).updateLostTracker(reqModel);

    }

}
