package com.tracker.cat.service;

import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.presentation.model.CatTrackerModel;
import com.tracker.cat.service.dto.CatTrackerReqDto;
import org.springframework.stereotype.Component;

@Component
public class CatTrackerMapper {


    public CatTrackerReqDto convertModelToCatTrackerDto(final CatTrackerModel catTrackerModel) throws CatTrackerInternalException {
        return new CatTrackerReqDto(
                catTrackerModel.getOwnerId(), catTrackerModel.getTrackerType(),
                catTrackerModel.isInZone(),
                catTrackerModel.isLostTracker());

    }

}
