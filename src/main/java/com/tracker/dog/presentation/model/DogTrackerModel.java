package com.tracker.dog.presentation.model;

import com.tracker.common.presentation.model.PetTrackerModel;
import com.tracker.dog.DogConstants;
import lombok.Getter;

@Getter
public class DogTrackerModel extends PetTrackerModel {

    public DogTrackerModel(Integer ownerId,
                           DogTrackerType dogTrackerType, boolean inZone) {
        super(DogConstants.PET_TYPE_DOG, dogTrackerType.name(), inZone, ownerId);
    }
}
