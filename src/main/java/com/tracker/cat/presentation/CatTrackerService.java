package com.tracker.cat.presentation;

import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.presentation.model.CatLostTrackerReqModel;
import com.tracker.cat.presentation.model.CatLostTrackerResModel;
import com.tracker.cat.presentation.model.CatTrackerModel;
import com.tracker.common.presentation.model.PetTrackerResponseModel;

public interface CatTrackerService {

    PetTrackerResponseModel createCatTracker(final CatTrackerModel catTrackerModel) throws
            CatTrackerException, CatTrackerInternalException;

    CatLostTrackerResModel updateLostTracker(final CatLostTrackerReqModel reqModel) throws CatTrackerException;

}
