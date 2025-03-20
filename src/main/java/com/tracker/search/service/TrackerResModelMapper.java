package com.tracker.search.service;

import com.tracker.common.service.dto.PetTrackerResDto;
import com.tracker.search.SearchTrackerException;
import com.tracker.search.presentation.model.CatTrackerModel;
import com.tracker.search.presentation.model.DogTrackerModel;
import com.tracker.search.presentation.model.TrackerModel;
import com.tracker.search.presentation.model.TrackerSearchResModel;
import com.tracker.search.service.dto.CatTrackerSearchResDto;
import com.tracker.search.service.dto.DogTrackerSearchResDto;
import com.tracker.search.service.dto.PetTrackerSearchResultsDto;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class TrackerResModelMapper {
    private final DozerBeanMapper mapper = new DozerBeanMapper();

    public TrackerSearchResModel mapTrackerResModel(PetTrackerSearchResultsDto trackerSearchResDto) throws SearchTrackerException {
        TrackerSearchResModel trackerSearchResModel = new TrackerSearchResModel();
        for (PetTrackerResDto trackerDto : trackerSearchResDto.getTrackerSearchModelList()) {
            TrackerModel resModel = mapTrackerResModel(trackerDto);
            if (resModel instanceof DogTrackerModel dogTrackerModel) {
                trackerSearchResModel.getDogTrackerModelList().add(dogTrackerModel);
            } else if (resModel instanceof CatTrackerModel catTrackerModel) {
                trackerSearchResModel.getCatTrackerModelList().add(catTrackerModel);
            }
        }
        return trackerSearchResModel;

    }

    public TrackerModel mapTrackerResModel(PetTrackerResDto petTrackerResDto) throws SearchTrackerException {
        if (petTrackerResDto instanceof DogTrackerSearchResDto dogTrackerSearchResDto) {
            return mapper.map(dogTrackerSearchResDto, DogTrackerModel.class);
        } else if (petTrackerResDto instanceof CatTrackerSearchResDto catTrackerSearchResDto) {
            return mapper.map(catTrackerSearchResDto, CatTrackerModel.class);
        }
        throw new SearchTrackerException("Invalid type of TrackerResDto:" + petTrackerResDto.getClass().getName());
    }


}
