package com.tracker.search.presentation.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DogTrackerModel extends TrackerModel {
    public DogTrackerModel(String trackerId, String trackerType, String petType, boolean inZone) {
        super(trackerId, trackerType, petType, inZone);
    }
}
