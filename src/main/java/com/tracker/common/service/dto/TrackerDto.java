package com.tracker.common.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class TrackerDto {

    private String petType;
    private Integer ownerId;

}
