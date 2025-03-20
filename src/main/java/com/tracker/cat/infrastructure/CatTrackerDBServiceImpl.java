package com.tracker.cat.infrastructure;

import com.tracker.cat.CatConstants;
import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.service.CatTrackerDBService;
import com.tracker.cat.service.dto.CatTrackerReqDto;
import com.tracker.entity.EntityException;
import com.tracker.entity.Pet;
import com.tracker.entity.Tracker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatTrackerDBServiceImpl implements CatTrackerDBService {


    private final CatTrackerRepository catTrackerRepository;


    @Override
    public long registerCatTracker(CatTrackerReqDto catTrackerReqDto) throws CatTrackerInternalException {

        Pet pet;
        try {
            pet = new Pet(catTrackerReqDto.getOwnerId(), catTrackerReqDto.getPetType(),
                    catTrackerReqDto.getTrackerType(),
                    catTrackerReqDto.isInZone(),
                    catTrackerReqDto.isLostTracker());
        } catch (EntityException e) {
            throw new CatTrackerInternalException(e.getMessage(), e);
        }
        Pet catEntity = catTrackerRepository.save(pet);
        log.info("Cat tracker saved to DB with tracker Id:" + pet.getTracker().getId());
        return catEntity.getTracker().getId();
    }

    @Override
    public void updateLostTrackerStatus(Long trackerId, Boolean lostTrackerStatus) throws CatTrackerException {
        Optional<Pet> pet = catTrackerRepository.findByTracker_Id(trackerId);
        if (pet.isEmpty()) {
            throw new CatTrackerException("No Pet Data found for TrackerID:" + trackerId);
        } else if (pet.get().getTracker() == null) {
            throw new CatTrackerException("Tracker not found for TrackerID:" + trackerId);
        }
        if (!CatConstants.PET_TYPE_CAT.equals(pet.get().getPetType())) {
            throw new CatTrackerException("InValid Pet Type provided:" + pet.get().getPetType());
        }
        Tracker tracker = pet.get().getTracker();
        tracker.setLostTracker(lostTrackerStatus);
        catTrackerRepository.save(pet.get());
        log.info("Cat lost tracker status updated into the DB");

    }
}
