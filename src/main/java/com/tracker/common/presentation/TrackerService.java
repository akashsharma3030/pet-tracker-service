package com.tracker.common.presentation;

import com.tracker.common.presentation.model.TrackerResModel;
import com.tracker.search.NoDataFoundException;

public interface TrackerService {

    TrackerResModel updateInZoneTrackerStatus(final Long trackerId, final boolean outOfZone) throws NoDataFoundException;

}
