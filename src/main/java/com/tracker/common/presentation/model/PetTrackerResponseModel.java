package com.tracker.common.presentation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Setter
public class PetTrackerResponseModel {

    private String message;
    private Instant timeStamp;

    private String trackerId;

    public PetTrackerResponseModel(Long trackerId, String message) {
        this.trackerId = String.valueOf(trackerId);
        this.message = message;
        this.timeStamp = Instant.now();
    }

    @Override
    public boolean equals(Object obj) {
        PetTrackerResponseModel responseModel = (PetTrackerResponseModel) obj;
        return this.trackerId.equals(responseModel.getTrackerId()) && this.message.equals(responseModel.getMessage())
                && this.timeStamp.equals(responseModel.getTimeStamp());
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.trackerId);
    }
}
