package com.tracker.common.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class PetTrackerModel {
    private String petType;
    private String trackerType;
    private boolean inZone;
    private Integer ownerId;
}
