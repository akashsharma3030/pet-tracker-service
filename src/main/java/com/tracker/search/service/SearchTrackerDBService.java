package com.tracker.search.service;

import com.tracker.common.service.dto.PetTrackerResDto;
import com.tracker.entity.EntityException;
import com.tracker.search.NoDataFoundException;
import com.tracker.search.service.dto.PetTrackerSearchResultsDto;

public interface SearchTrackerDBService {

    PetTrackerResDto searchTrackerById(final Long trackerId) throws NoDataFoundException, EntityException;

    PetTrackerSearchResultsDto searchByPetAndTrackerType(final String pet, final String trackerType) throws EntityException, NoDataFoundException;

    Long getPetsOutsideOfZone(final String pet, final String trackerType) throws EntityException;
}
