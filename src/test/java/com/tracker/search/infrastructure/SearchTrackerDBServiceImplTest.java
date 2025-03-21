package com.tracker.search.infrastructure;

import com.tracker.cat.CatConstants;
import com.tracker.common.service.dto.PetTrackerResDto;
import com.tracker.dog.DogConstants;
import com.tracker.entity.EntityException;
import com.tracker.entity.Pet;
import com.tracker.entity.Tracker;
import com.tracker.search.NoDataFoundException;
import com.tracker.search.service.dto.PetTrackerSearchResultsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchTrackerDBServiceImplTest {

    @Mock
    private SearchTrackerRepository repository;

    @InjectMocks
    private SearchTrackerDBServiceImpl service;

    private Pet dogPet;

    @BeforeEach
    void setUp() {
        dogPet = new Pet();
        dogPet.setPetType(DogConstants.PET_TYPE_DOG);
        dogPet.setTracker(new Tracker());
        dogPet.getTracker().setId(1L);
        dogPet.getTracker().setTrackerType("BIG");
        dogPet.getTracker().setInZone(true);
        dogPet.setOwnerId(1);

        Pet catPet = new Pet();
        catPet.setPetType(CatConstants.PET_TYPE_CAT);
        catPet.setTracker(new Tracker());
        catPet.getTracker().setId(2L);
        catPet.getTracker().setTrackerType("SMALL");
        catPet.getTracker().setLostTracker(false);
        catPet.getTracker().setInZone(true);
        catPet.setOwnerId(2);
    }

    @Test
    void searchTrackerById_Success() throws NoDataFoundException, EntityException {
        when(repository.findByTracker_Id(1L)).thenReturn(Optional.of(dogPet));

        PetTrackerResDto result = service.searchTrackerById(1L);

        assertNotNull(result);
        assertEquals(DogConstants.PET_TYPE_DOG, result.getPetType());
        verify(repository).findByTracker_Id(1L);
    }

    @Test
    void searchTrackerById_NoDataFound() {
        when(repository.findByTracker_Id(1L)).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> service.searchTrackerById(1L));
        verify(repository).findByTracker_Id(1L);
    }

    @Test
    void searchByPetAndTrackerType_Success() throws EntityException, NoDataFoundException {
        when(repository.findByPetTypeAndTracker_TrackerType(DogConstants.PET_TYPE_DOG, "BIG"))
                .thenReturn(Optional.of(List.of(dogPet)));

        PetTrackerSearchResultsDto result = service.searchByPetAndTrackerType(DogConstants.PET_TYPE_DOG, "BIG");

        assertNotNull(result);
        assertFalse(result.getTrackerSearchModelList().isEmpty());
        verify(repository).findByPetTypeAndTracker_TrackerType(DogConstants.PET_TYPE_DOG, "BIG");
    }

    @Test
    void searchByPetAndTrackerType_NoDataFound() {
        when(repository.findByPetTypeAndTracker_TrackerType(DogConstants.PET_TYPE_DOG, "BIG"))
                .thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> service.searchByPetAndTrackerType(DogConstants.PET_TYPE_DOG, "BIG"));
        verify(repository).findByPetTypeAndTracker_TrackerType(DogConstants.PET_TYPE_DOG, "BIG");
    }

    @Test
    void getPetsOutsideOfZone_Success() {
        when(repository.countByPetTypeAndTracker_TrackerTypeAndTracker_InZone(DogConstants.PET_TYPE_DOG, "BIG", false))
                .thenReturn(Optional.of(5L));

        Long result = service.getPetsOutsideOfZone(DogConstants.PET_TYPE_DOG, "BIG");

        assertEquals(5L, result);
        verify(repository).countByPetTypeAndTracker_TrackerTypeAndTracker_InZone(DogConstants.PET_TYPE_DOG, "BIG", false);
    }

    @Test
    void getPetsOutsideOfZone_NoDataFound() {
        when(repository.countByPetTypeAndTracker_TrackerTypeAndTracker_InZone(DogConstants.PET_TYPE_DOG, "BIG", false))
                .thenReturn(Optional.empty());

        Long result = service.getPetsOutsideOfZone(DogConstants.PET_TYPE_DOG, "BIG");

        assertEquals(0L, result);
        verify(repository).countByPetTypeAndTracker_TrackerTypeAndTracker_InZone(DogConstants.PET_TYPE_DOG, "BIG", false);
    }
}