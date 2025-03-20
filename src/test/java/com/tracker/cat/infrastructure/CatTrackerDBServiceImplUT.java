package com.tracker.cat.infrastructure;

import com.tracker.cat.CatConstants;
import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.presentation.model.CatTrackerType;
import com.tracker.cat.service.dto.CatTrackerReqDto;
import com.tracker.entity.Pet;
import com.tracker.entity.Tracker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatTrackerDBServiceImplUT {

    @InjectMocks
    private CatTrackerDBServiceImpl catTrackerDBService;

    @Mock
    private CatTrackerRepository catTrackerRepository;

    @Test
    void testRegisterCatTracker_Success() throws CatTrackerInternalException {
        CatTrackerReqDto catTrackerReqDto =
                new CatTrackerReqDto(123, CatTrackerType.BIG.name(), true, true);
        Pet pet = new Pet();
        Tracker tracker = new Tracker();
        tracker.setId(1L);
        tracker.setTrackerType(CatTrackerType.BIG.name());
        pet.setTracker(tracker);
        when(catTrackerRepository.save(any(Pet.class))).thenReturn(pet);
        Long trackerId = catTrackerDBService.registerCatTracker(catTrackerReqDto);
        assertEquals(1, trackerId);
        verify(catTrackerRepository).save(any(Pet.class));
    }

    @Test
    void testLostTracker() throws CatTrackerException {
        Pet pet = new Pet();
        pet.setPetType(CatConstants.PET_TYPE_CAT);
        Tracker tracker = new Tracker();
        tracker.setId(1L);
        tracker.setLostTracker(true);
        tracker.setTrackerType(CatTrackerType.BIG.name());
        pet.setTracker(tracker);
        when(catTrackerRepository.findByTracker_Id(1L)).thenReturn(Optional.of(pet));
        when(catTrackerRepository.save(any(Pet.class))).thenReturn(pet);
        catTrackerDBService.updateLostTrackerStatus(1L, true);
        verify(catTrackerRepository).save(any(Pet.class));
    }

}
