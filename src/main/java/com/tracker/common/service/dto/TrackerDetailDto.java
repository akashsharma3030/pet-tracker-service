package com.tracker.common.service.dto;

import lombok.Getter;

@Getter
public class TrackerDetailDto {

    private final String trackerType;
    private final boolean inZone;

    private Long trackerId;

    public TrackerDetailDto(String trackerType, boolean inZone) {
        this.trackerType = trackerType;
        this.inZone = inZone;
    }

    public TrackerDetailDto(String trackerType, boolean inZone, Long trackerId) {
        this.trackerType = trackerType;
        this.inZone = inZone;
        this.trackerId = trackerId;
    }
}
