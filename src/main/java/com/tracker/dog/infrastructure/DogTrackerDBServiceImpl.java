package com.tracker.dog.infrastructure;

import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.service.DogTrackerDBService;
import com.tracker.dog.service.dto.DogTrackerReqDto;
import com.tracker.entity.EntityException;
import com.tracker.entity.Pet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DogTrackerDBServiceImpl implements DogTrackerDBService {


    private final DogTrackerRepository dogTrackerRepository;


    @Override
    public long registerDogTracker(DogTrackerReqDto dogTrackerReqDto) throws DogTrackerInternalException {

        Pet pet;
        try {
            pet = new Pet(dogTrackerReqDto.getOwnerId(), dogTrackerReqDto.getPetType(),
                    dogTrackerReqDto.getTrackerType(),
                    dogTrackerReqDto.isInZone());
        } catch (EntityException e) {
            throw new DogTrackerInternalException(e.getMessage(), e);
        }
        Pet petEntity = dogTrackerRepository.save(pet);
        log.info("Dog Tracker Saved successfully with trackerId:" + petEntity.getTracker().getId());
        return petEntity.getTracker().getId();
    }
}
