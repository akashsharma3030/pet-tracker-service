package com.tracker.common.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PetTrackerResDto {

    private String petType;
    private String trackerType;
    private boolean inZone;
    private Integer ownerId;
    private String trackerId;


}
