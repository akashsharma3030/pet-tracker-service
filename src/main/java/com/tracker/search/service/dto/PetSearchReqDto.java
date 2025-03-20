package com.tracker.search.service.dto;

import com.tracker.common.presentation.model.PetType;
import com.tracker.common.presentation.model.TrackerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PetSearchReqDto {

    private Integer ownerId;
    private PetType petType;
    private TrackerType trackerType;
    private Long trackerId;

}
