package com.tracker.dog.presentation;

import com.tracker.common.presentation.model.PetTrackerResponseModel;
import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.presentation.model.DogTrackerModel;

public interface DogTrackerService {

    PetTrackerResponseModel createDogTracker(final DogTrackerModel dogTrackerModel) throws
            DogTrackerException, DogTrackerInternalException;

}
