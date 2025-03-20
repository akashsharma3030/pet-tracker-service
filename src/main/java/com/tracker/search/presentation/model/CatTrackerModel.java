package com.tracker.search.presentation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatTrackerModel extends TrackerModel {

    private boolean lostTracker;

    public CatTrackerModel(String trackerId, String trackerType, String petType, boolean lostTracker,
                           boolean inZone) {
        super(trackerId, trackerType, petType, inZone);
        this.lostTracker = lostTracker;
    }
}
