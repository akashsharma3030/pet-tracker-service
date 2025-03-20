package com.tracker.common.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PetTrackerDto {

    private String petType;
    private String trackerType;
    private boolean inZone;
    private Integer ownerId;

    public PetTrackerDto(String petType, boolean inZone, Integer ownerId) {
        this.petType = petType;
        this.inZone = inZone;
        this.ownerId = ownerId;
    }
}
