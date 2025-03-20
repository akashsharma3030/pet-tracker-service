package com.tracker.search.service.dto;

import com.tracker.common.service.dto.PetTrackerResDto;

public class DogTrackerSearchResDto extends PetTrackerResDto {
    public DogTrackerSearchResDto(String trackerId, String trackerType, String petType, boolean inZone, Integer ownerId) {
        super(petType, trackerType, inZone, ownerId, trackerId);
    }
}
