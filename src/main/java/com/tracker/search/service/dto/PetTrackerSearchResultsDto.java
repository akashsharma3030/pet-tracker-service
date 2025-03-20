package com.tracker.search.service.dto;

import com.tracker.common.service.dto.PetTrackerResDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PetTrackerSearchResultsDto {

    private final List<PetTrackerResDto> trackerSearchModelList = new ArrayList<>();


}
