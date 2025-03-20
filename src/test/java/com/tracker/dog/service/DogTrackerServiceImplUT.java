package com.tracker.dog.service;

import com.tracker.common.presentation.model.PetTrackerResponseModel;
import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.presentation.model.DogTrackerModel;
import com.tracker.dog.presentation.model.DogTrackerType;
import com.tracker.dog.service.dto.DogTrackerReqDto;
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
class DogTrackerServiceImplTest {

    @Mock
    private DogTrackerDBService dogTrackerDBService;

    @Mock
    private DogTrackerMapper mapper;

    @InjectMocks
    private DogTrackerServiceImpl dogTrackerService;

    @Test
    void createDogTracker_Success() throws DogTrackerInternalException {
        // Arrange
        DogTrackerModel inputModel = new DogTrackerModel(123, DogTrackerType.BIG, true);
        long expectedTrackerId = 456L;

        when(mapper.convertModelToDogTrackerDto(inputModel)).thenReturn(new DogTrackerReqDto(123, DogTrackerType.BIG.name(), true));
        when(dogTrackerDBService.registerDogTracker(any(DogTrackerReqDto.class))).thenReturn(expectedTrackerId);

        // Act
        PetTrackerResponseModel response = dogTrackerService.createDogTracker(inputModel);

        // Assert
        assertNotNull(response);
        assertEquals(expectedTrackerId, response.getTrackerId());
        assertEquals("Dog Tracker Registered Successfully", response.getMessage());

        // Verify interactions
        verify(mapper).convertModelToDogTrackerDto(inputModel);
        verify(dogTrackerDBService).registerDogTracker(any(DogTrackerReqDto.class));
    }

    @Test
    void createDogTracker_DBServiceThrowsException() throws DogTrackerInternalException {
        // Arrange
        DogTrackerModel inputModel = new DogTrackerModel(123, DogTrackerType.BIG, true);
        DogTrackerInternalException dbException = new DogTrackerInternalException("Database error");

        when(mapper.convertModelToDogTrackerDto(inputModel)).thenReturn(new DogTrackerReqDto(123, DogTrackerType.BIG.name(), true));
        when(dogTrackerDBService.registerDogTracker(any(DogTrackerReqDto.class))).thenThrow(dbException);

        // Act & Assert
        DogTrackerInternalException exception = assertThrows(DogTrackerInternalException.class,
                () -> dogTrackerService.createDogTracker(inputModel)
        );

        assertEquals(dbException.getMessage(), exception.getMessage());

        // Verify interactions
        verify(mapper).convertModelToDogTrackerDto(inputModel);
        verify(dogTrackerDBService).registerDogTracker(any(DogTrackerReqDto.class));
    }

}