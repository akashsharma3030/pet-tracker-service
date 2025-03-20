package com.tracker.common.service;

import com.tracker.common.presentation.TrackerService;
import com.tracker.common.presentation.model.TrackerResModel;
import com.tracker.search.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackerServiceImpl implements TrackerService {

    private final TrackerDBService trackerDBService;

    @Override
    public TrackerResModel updateInZoneTrackerStatus(final Long trackerId, final boolean inZone) throws NoDataFoundException {
        trackerDBService.updateOutOfZone(trackerId, inZone);
        return new TrackerResModel("Zone status for tracker Updated successfully");
    }
}
