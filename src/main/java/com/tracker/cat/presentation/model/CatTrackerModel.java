package com.tracker.cat.presentation.model;

import com.tracker.cat.CatConstants;
import com.tracker.common.presentation.model.PetTrackerModel;
import lombok.Getter;

@Getter
public class CatTrackerModel extends PetTrackerModel {
    private final boolean lostTracker;

    public CatTrackerModel(Integer ownerId,
                           CatTrackerType catTrackerType, boolean inZone,
                           boolean lostTracker) {
        super(CatConstants.PET_TYPE_CAT, catTrackerType.name(), inZone, ownerId);
        this.lostTracker = lostTracker;

    }
}
