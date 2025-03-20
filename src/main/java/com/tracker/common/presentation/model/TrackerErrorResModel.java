package com.tracker.common.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TrackerErrorResModel {

    private Instant timeStamp;
    private List<String> errorMessage;

}
