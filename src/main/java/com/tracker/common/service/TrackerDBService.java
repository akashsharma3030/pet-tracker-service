package com.tracker.common.service;

import com.tracker.search.NoDataFoundException;

public interface TrackerDBService {

    void updateOutOfZone(final Long taskId, final boolean outOfZone) throws NoDataFoundException;

}
