package com.tracker.cat.service;

import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.presentation.model.CatLostTrackerReqModel;
import com.tracker.cat.presentation.model.CatTrackerModel;
import com.tracker.cat.presentation.model.CatTrackerType;
import com.tracker.cat.service.dto.CatTrackerReqDto;
import com.tracker.common.presentation.model.PetTrackerResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTrackerServiceImplTest {

    @Mock
    private CatTrackerDBService catTrackerDBService;

    @Mock
    private CatTrackerMapper mapper;

    @InjectMocks
    private CatTrackerServiceImpl catTrackerService;

    @Test
    void createCatTracker_Success() throws CatTrackerInternalException {
        // Arrange
        CatTrackerModel inputModel = new CatTrackerModel(123, CatTrackerType.BIG, true, true);
        long expectedTrackerId = 456L;

        when(mapper.convertModelToCatTrackerDto(inputModel)).thenReturn(new CatTrackerReqDto(123, CatTrackerType.BIG.name(), true, true));
        when(catTrackerDBService.registerCatTracker(any(CatTrackerReqDto.class))).thenReturn(expectedTrackerId);

        // Act
        PetTrackerResponseModel response = catTrackerService.createCatTracker(inputModel);

        // Assert
        assertNotNull(response);
        assertEquals(expectedTrackerId, response.getTrackerId());
        assertEquals("Cat Tracker Registered Successfully", response.getMessage());

        // Verify interactions
        verify(mapper).convertModelToCatTrackerDto(inputModel);
        verify(catTrackerDBService).registerCatTracker(any(CatTrackerReqDto.class));
    }

    @Test
    void createCatTracker_ThrowsException() throws CatTrackerInternalException {
        // Arrange
        CatTrackerModel inputModel = new CatTrackerModel(123, CatTrackerType.BIG, true, true);
        CatTrackerInternalException catTrackerInternalException = new CatTrackerInternalException("Database error");

        when(mapper.convertModelToCatTrackerDto(inputModel)).thenReturn(new CatTrackerReqDto(123, CatTrackerType.BIG.name(), true, true));
        when(catTrackerDBService.registerCatTracker(any(CatTrackerReqDto.class))).thenThrow(catTrackerInternalException);

        // Act & Assert
        CatTrackerInternalException exception = assertThrows(CatTrackerInternalException.class,
                () -> catTrackerService.createCatTracker(inputModel)
        );

        assertEquals(catTrackerInternalException.getMessage(), exception.getMessage());
        // Verify interactions
        verify(mapper).convertModelToCatTrackerDto(inputModel);
        verify(catTrackerDBService).registerCatTracker(any(CatTrackerReqDto.class));
    }


    @Test
    void updateLostTracker_Success() throws CatTrackerException {
        CatLostTrackerReqModel reqModel = new CatLostTrackerReqModel();
        reqModel.setTrackerId(1L);
        reqModel.setLostTracker(true);
        catTrackerService.updateLostTracker(reqModel);
        verify(catTrackerDBService).updateLostTrackerStatus(1L, true);
    }

    @Test
    void updateLostTracker_ThrowsException() throws CatTrackerException {
        CatLostTrackerReqModel reqModel = new CatLostTrackerReqModel();
        reqModel.setTrackerId(1L);
        reqModel.setLostTracker(true);
        CatTrackerException catTrackerException = new CatTrackerException("Exception");
        doThrow(catTrackerException).when(catTrackerDBService).updateLostTrackerStatus(1L, true);
        assertThrows(CatTrackerException.class,
                () -> catTrackerService.updateLostTracker(reqModel)
        );
        verify(catTrackerDBService).updateLostTrackerStatus(1L, true);
    }


}