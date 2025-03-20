package com.tracker.common.presentation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TrackerResModel {

    private String message;
    private Instant timeStamp;

    public TrackerResModel(String message) {
        this.message = message;
        this.timeStamp = Instant.now();
    }

}
