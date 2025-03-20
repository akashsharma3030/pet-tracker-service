package com.tracker.cat.presentation.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatLostTrackerReqModel {

    @NotNull(message = "tracker must be valid and not null")
    private Long trackerId;
    @NotNull(message = "lost tracker flag must be true or false")
    private Boolean lostTracker;

}
