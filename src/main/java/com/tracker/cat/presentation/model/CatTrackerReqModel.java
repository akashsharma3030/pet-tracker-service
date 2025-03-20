package com.tracker.cat.presentation.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CatTrackerReqModel {

    @NotNull(message = "Cat Tracker Type cannot be null")
    private CatTrackerType catTrackerType;
    @NotNull(message = "OwnerId cannot be null")
    private Integer ownerId;


}
