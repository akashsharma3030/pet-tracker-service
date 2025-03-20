package com.tracker.search.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrackerSearchReqModel {

    private String petType;
    private Long trackerId;
    private String trackerType;
    private boolean InZone;

}
