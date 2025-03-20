package com.tracker.cat.service;

import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.presentation.CatTrackerService;
import com.tracker.cat.presentation.model.CatLostTrackerReqModel;
import com.tracker.cat.presentation.model.CatLostTrackerResModel;
import com.tracker.cat.presentation.model.CatTrackerModel;
import com.tracker.common.presentation.model.PetTrackerResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatTrackerServiceImpl implements CatTrackerService {

    private final CatTrackerDBService catTrackerDBService;
    private final CatTrackerMapper mapper;

    @Override
    public PetTrackerResponseModel createCatTracker(final CatTrackerModel catTrackerModel) throws CatTrackerInternalException {
        long trackerId =
                catTrackerDBService.registerCatTracker(mapper.convertModelToCatTrackerDto(catTrackerModel));
        log.info("Tracker ID create for Cat Tracker:" + trackerId);
        return new PetTrackerResponseModel(trackerId, "Cat Tracker Registered Successfully");
    }


    @Override
    public CatLostTrackerResModel updateLostTracker(final CatLostTrackerReqModel reqModel) throws CatTrackerException {
        catTrackerDBService.updateLostTrackerStatus(reqModel.getTrackerId(), reqModel.getLostTracker());
        return new CatLostTrackerResModel("Lost tracker Updated Successfully");
    }
}
