package com.tracker.cat.service;

import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.service.dto.CatTrackerReqDto;

public interface CatTrackerDBService {

    long registerCatTracker(final CatTrackerReqDto catTrackerReqDto) throws CatTrackerInternalException;

    void updateLostTrackerStatus(final Long trackerId, final Boolean lostTrackerStatus) throws CatTrackerException;
}
