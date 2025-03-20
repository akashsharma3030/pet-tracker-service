package com.tracker.search.presentation;

import com.tracker.common.presentation.model.PetType;
import com.tracker.common.presentation.model.TrackerType;
import com.tracker.entity.EntityException;
import com.tracker.search.NoDataFoundException;
import com.tracker.search.SearchTrackerException;
import com.tracker.search.presentation.model.TrackerModel;
import com.tracker.search.presentation.model.TrackerSearchListResModel;
import com.tracker.search.presentation.model.TrackerSearchRespModel;

public interface SearchTrackerService {

    TrackerSearchListResModel searchByPetTypeTrackerType(final PetType petType, final TrackerType trackerType) throws EntityException, NoDataFoundException, SearchTrackerException;

    TrackerSearchRespModel searchByTrackerId(final Long trackerId) throws NoDataFoundException, EntityException, SearchTrackerException;

    Long getPetsOutSideOfZone(final PetType petType, final TrackerType trackerType) throws EntityException;

}
