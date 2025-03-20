package com.tracker.dog.service;

import com.tracker.common.presentation.model.PetTrackerResponseModel;
import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.presentation.DogTrackerService;
import com.tracker.dog.presentation.model.DogTrackerModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DogTrackerServiceImpl implements DogTrackerService {

    private final DogTrackerDBService dogTrackerDBService;
    private final DogTrackerMapper mapper;


    @Override
    public PetTrackerResponseModel createDogTracker(final DogTrackerModel dogTrackerModel) throws DogTrackerInternalException {
        long trackerId =
                dogTrackerDBService.registerDogTracker(mapper.convertModelToDogTrackerDto(dogTrackerModel));
        log.info("Generated tracker Id:" + trackerId);
        return new PetTrackerResponseModel(trackerId, "Dog Tracker Registered Successfully");

    }
}
