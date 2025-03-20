package com.tracker.dog.presentation;

import com.tracker.common.presentation.model.PetTrackerResponseModel;
import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.presentation.model.DogTrackerModel;
import com.tracker.dog.presentation.model.DogTrackerReqModel;
import com.tracker.dog.presentation.model.DogTrackerType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogTrackerControllerTest {

    @Mock
    private DogTrackerService dogTrackerService;

    @InjectMocks
    private DogTrackerController dogTrackerController;

    @Test
    void registerDogTracker_Success() throws DogTrackerException, DogTrackerInternalException {
        // Arrange
        DogTrackerReqModel reqModel = new DogTrackerReqModel(DogTrackerType.BIG,
                12345);

        PetTrackerResponseModel expectedResponse = new PetTrackerResponseModel(123L,
                "Dog Tracker created");

        when(dogTrackerService.createDogTracker(any(DogTrackerModel.class)))
                .thenReturn(expectedResponse);

        // Act
        PetTrackerResponseModel actualResponse = dogTrackerController.registerDogTracker(reqModel);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    void registerDogTracker_ServiceThrowsException() throws DogTrackerException, DogTrackerInternalException {

        DogTrackerReqModel reqModel = new DogTrackerReqModel(DogTrackerType.BIG,
                12345);

        when(dogTrackerService.createDogTracker(any(DogTrackerModel.class)))
                .thenThrow(new DogTrackerException("Service error"));

        assertThrows(DogTrackerException.class, () -> {
            dogTrackerController.registerDogTracker(reqModel);
        });

    }
}