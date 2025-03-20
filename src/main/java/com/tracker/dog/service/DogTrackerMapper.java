package com.tracker.dog.service;

import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.presentation.model.DogTrackerModel;
import com.tracker.dog.service.dto.DogTrackerReqDto;
import org.springframework.stereotype.Component;

@Component
public class DogTrackerMapper {


    public DogTrackerReqDto convertModelToDogTrackerDto(final DogTrackerModel dogTrackerModel) throws DogTrackerInternalException {
        return new DogTrackerReqDto(
                dogTrackerModel.getOwnerId(), dogTrackerModel.getTrackerType(),
                dogTrackerModel.isInZone());

    }

}
