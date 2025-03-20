package com.tracker.search.service;

import com.tracker.common.presentation.model.PetType;
import com.tracker.common.presentation.model.TrackerType;
import com.tracker.common.service.dto.PetTrackerResDto;
import com.tracker.entity.EntityException;
import com.tracker.search.NoDataFoundException;
import com.tracker.search.SearchTrackerException;
import com.tracker.search.presentation.model.TrackerModel;
import com.tracker.search.presentation.model.TrackerSearchListResModel;
import com.tracker.search.presentation.model.TrackerSearchRespModel;
import com.tracker.search.service.dto.PetTrackerSearchResultsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchTrackerServiceImplUT {

    @Mock
    private SearchTrackerDBService searchTrackerDBService;

    @Mock
    private TrackerResModelMapper trackerResModelMapper;

    @InjectMocks
    private SearchTrackerServiceImpl service;

    @Test
    void searchByPetTypeTrackerType_Success() throws EntityException, NoDataFoundException, SearchTrackerException {
        // Given
        PetType petType = PetType.DOG;
        TrackerType trackerType = TrackerType.BIG;
        PetTrackerSearchResultsDto dto = new PetTrackerSearchResultsDto();
        TrackerSearchListResModel expectedModel = new TrackerSearchListResModel();

        when(searchTrackerDBService.searchByPetAndTrackerType(petType.name(), trackerType.name()))
                .thenReturn(dto);
        when(trackerResModelMapper.mapTrackerResModel(dto)).thenReturn(expectedModel);

        // When
        TrackerSearchListResModel result = service.searchByPetTypeTrackerType(petType, trackerType);

        // Then
        assertSame(expectedModel, result);
        verify(searchTrackerDBService).searchByPetAndTrackerType(petType.name(), trackerType.name());
        verify(trackerResModelMapper).mapTrackerResModel(dto);
    }

    @Test
    void searchByPetTypeTrackerType_EntityException() throws EntityException, NoDataFoundException {
        // Given
        PetType petType = PetType.CAT;
        TrackerType trackerType = TrackerType.BIG;
        when(searchTrackerDBService.searchByPetAndTrackerType(anyString(), anyString()))
                .thenThrow(new EntityException("DB error"));

        // Then
        assertThrows(EntityException.class, () ->
                service.searchByPetTypeTrackerType(petType, trackerType));
    }

    @Test
    void searchByPetTypeTrackerType_NoDataFoundException() throws EntityException, NoDataFoundException {
        // Given
        PetType petType = PetType.DOG;
        TrackerType trackerType = TrackerType.BIG;
        when(searchTrackerDBService.searchByPetAndTrackerType(anyString(), anyString()))
                .thenThrow(new NoDataFoundException("No data"));

        // Then
        assertThrows(NoDataFoundException.class, () ->
                service.searchByPetTypeTrackerType(petType, trackerType));
    }

    @Test
    void searchByTrackerId_Success() throws NoDataFoundException, EntityException, SearchTrackerException {
        // Given
        Long trackerId = 123L;
        PetTrackerResDto dto = new PetTrackerResDto(PetType.DOG.name(), TrackerType.BIG.name(),
                true, 123, "12345");
        TrackerSearchRespModel expectedModel = new  TrackerSearchRespModel();


        when(searchTrackerDBService.searchTrackerById(trackerId)).thenReturn(dto);
        when(trackerResModelMapper.mapTrackerSearchRespModel(dto)).thenReturn(expectedModel);

        // When
        TrackerSearchRespModel result = service.searchByTrackerId(trackerId);

        // Then
        assertSame(expectedModel, result);
        verify(searchTrackerDBService).searchTrackerById(trackerId);
        verify(trackerResModelMapper).mapTrackerSearchRespModel(dto);
    }

    @Test
    void searchByTrackerId_NoDataFoundException() throws EntityException, NoDataFoundException {
        // Given
        Long trackerId = 456L;
        when(searchTrackerDBService.searchTrackerById(trackerId))
                .thenThrow(new NoDataFoundException("Not found"));

        // Then
        assertThrows(NoDataFoundException.class, () -> service.searchByTrackerId(trackerId));
    }

    @Test
    void searchByTrackerId_EntityException() throws EntityException, NoDataFoundException {
        // Given
        Long trackerId = 789L;
        when(searchTrackerDBService.searchTrackerById(trackerId))
                .thenThrow(new EntityException("DB error"));

        // Then
        assertThrows(EntityException.class, () -> service.searchByTrackerId(trackerId));
    }

    @Test
    void getPetsOutSideOfZone_Success() throws EntityException {
        // Given
        PetType petType = PetType.DOG;
        TrackerType trackerType = TrackerType.SMALL;
        Long expectedCount = 5L;

        when(searchTrackerDBService.getPetsOutsideOfZone(petType.name(), trackerType.name()))
                .thenReturn(expectedCount);

        // When
        Long result = service.getPetsOutSideOfZone(petType, trackerType);

        // Then
        assertEquals(expectedCount, result);
        verify(searchTrackerDBService).getPetsOutsideOfZone(petType.name(), trackerType.name());
    }

    @Test
    void getPetsOutSideOfZone_EntityException() throws EntityException {
        // Given
        PetType petType = PetType.CAT;
        TrackerType trackerType = TrackerType.BIG;
        when(searchTrackerDBService.getPetsOutsideOfZone(anyString(), anyString()))
                .thenThrow(new EntityException("DB error"));

        // Then
        assertThrows(EntityException.class, () ->
                service.getPetsOutSideOfZone(petType, trackerType));
    }
}