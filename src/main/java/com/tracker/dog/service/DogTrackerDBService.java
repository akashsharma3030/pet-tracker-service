package com.tracker.dog.service;

import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.service.dto.DogTrackerReqDto;

public interface DogTrackerDBService {

    long registerDogTracker(final DogTrackerReqDto dogTrackerReqDto) throws DogTrackerInternalException;
}
