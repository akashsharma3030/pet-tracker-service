package com.tracker.search.service;

import com.tracker.common.presentation.model.PetType;
import com.tracker.common.presentation.model.TrackerType;
import com.tracker.common.service.dto.PetTrackerResDto;
import com.tracker.entity.EntityException;
import com.tracker.search.NoDataFoundException;
import com.tracker.search.SearchTrackerException;
import com.tracker.search.presentation.SearchTrackerService;
import com.tracker.search.presentation.model.TrackerModel;
import com.tracker.search.presentation.model.TrackerSearchListResModel;
import com.tracker.search.presentation.model.TrackerSearchRespModel;
import com.tracker.search.service.dto.PetTrackerSearchResultsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchTrackerServiceImpl implements SearchTrackerService {

    private final SearchTrackerDBService searchTrackerDBService;

    private final TrackerResModelMapper trackerResModelMapper;


    @Override
    public TrackerSearchListResModel searchByPetTypeTrackerType(final PetType petType, final TrackerType trackerType) throws EntityException, NoDataFoundException, SearchTrackerException {
        PetTrackerSearchResultsDto resultsDto = searchTrackerDBService.searchByPetAndTrackerType(petType.name(),
                trackerType.name());
        return trackerResModelMapper.mapTrackerResModel(resultsDto);
    }

    @Override
    public TrackerSearchRespModel searchByTrackerId(final Long trackerId) throws NoDataFoundException, EntityException, SearchTrackerException {
        PetTrackerResDto petTrackerResDto = searchTrackerDBService.searchTrackerById(trackerId);
        return trackerResModelMapper.mapTrackerSearchRespModel(petTrackerResDto);
    }

    @Override
    public Long getPetsOutSideOfZone(final PetType petType, final TrackerType trackerType) throws EntityException {
        return searchTrackerDBService.getPetsOutsideOfZone(petType.name(), trackerType.name());
    }
}
