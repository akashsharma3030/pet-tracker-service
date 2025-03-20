package com.tracker.dog.presentation.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DogTrackerReqModel {

    @NotNull(message = "Dog tracker type cannot be Null")
    private final DogTrackerType dogTrackerType;

    @NotNull(message = "Owner Id cannot be Null")
    private final Integer ownerId;

}
