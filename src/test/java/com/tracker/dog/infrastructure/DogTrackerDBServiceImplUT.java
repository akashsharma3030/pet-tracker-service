package com.tracker.dog.infrastructure;

import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.presentation.model.DogTrackerType;
import com.tracker.dog.service.dto.DogTrackerReqDto;
import com.tracker.entity.Pet;
import com.tracker.entity.Tracker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DogTrackerDBServiceImplUT {

    @InjectMocks
    private DogTrackerDBServiceImpl dogTrackerDBService;

    @Mock
    private DogTrackerRepository dogTrackerRepository;

    @Test
    void testRegisterDogTracker_Success() throws DogTrackerInternalException {
        DogTrackerReqDto dogTrackerReqDto =
                new DogTrackerReqDto(123, DogTrackerType.BIG.name(), true);
        Pet pet = new Pet();
        Tracker tracker = new Tracker();
        tracker.setId(1L);
        pet.setTracker(tracker);
        when(dogTrackerRepository.save(any(Pet.class))).thenReturn(pet);
        Long trackerId = dogTrackerDBService.registerDogTracker(dogTrackerReqDto);
        assertEquals(1, trackerId);
        verify(dogTrackerRepository).save(any(Pet.class));
    }

}
