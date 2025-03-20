package com.tracker.search.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TrackerModel {
    private String trackerId;
    private String trackerType;
    private String petType;
    private boolean inZone;

}
