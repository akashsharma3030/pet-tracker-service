package com.tracker.dog.service.dto;

import com.tracker.common.service.dto.PetTrackerDto;
import com.tracker.dog.DogConstants;
import com.tracker.dog.DogTrackerInternalException;


public class DogTrackerReqDto extends PetTrackerDto {
    public DogTrackerReqDto(Integer ownerId, String trackerType, boolean inZone) throws DogTrackerInternalException {
        super(DogConstants.PET_TYPE_DOG, trackerType, inZone, ownerId);
        if (!DogConstants.DOG_TRACKER_TYPE.contains(trackerType)) {
            throw new DogTrackerInternalException("For Dog wrong tracker type provided:" + trackerType);
        }
    }

}
