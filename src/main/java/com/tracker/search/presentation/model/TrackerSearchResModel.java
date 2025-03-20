package com.tracker.search.presentation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TrackerSearchResModel {

    private final List<DogTrackerModel> dogTrackerModelList = new ArrayList<>();
    private final List<CatTrackerModel> catTrackerModelList = new ArrayList<>();


}
