package com.tracker.search.service.dto;

import com.tracker.common.service.dto.PetTrackerResDto;

public class CatTrackerSearchResDto extends PetTrackerResDto {

    private final boolean lostTracker;

    public CatTrackerSearchResDto(String trackerId, String trackerType, String petType, boolean lostTracker,
                                  boolean inZone, Integer ownerId) {
        super(petType, trackerType, inZone, ownerId, trackerId);
        this.lostTracker = lostTracker;
    }
}
