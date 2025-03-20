package com.tracker.search.presentation.model;

import com.tracker.common.presentation.model.PetType;
import com.tracker.common.presentation.model.TrackerType;
import com.tracker.search.presentation.model.validator.PetTrackerTypeValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PetTrackerTypeValidation
public class PetTrackerSearchReqModel {

    private PetType petType;
    private TrackerType trackerType;

}
