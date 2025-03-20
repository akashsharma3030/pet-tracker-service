package com.tracker.cat.service.dto;

import com.tracker.cat.CatConstants;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.common.service.dto.PetTrackerDto;
import lombok.Getter;

@Getter
public class CatTrackerReqDto extends PetTrackerDto {

    private final boolean lostTracker;

    public CatTrackerReqDto(Integer ownerId, String trackerType, boolean inZone,
                            boolean lostTracker) throws CatTrackerInternalException {
        super(CatConstants.PET_TYPE_CAT, inZone, ownerId);
        if (!CatConstants.CAT_TRACKER_TYPE.contains(trackerType)) {
            throw new CatTrackerInternalException(" Invalid cat tracker type provided:" + trackerType);
        }
        this.setTrackerType(trackerType);
        this.lostTracker = lostTracker;
    }

}
