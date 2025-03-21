package com.tracker.search.infrastructure;

import com.tracker.cat.CatConstants;
import com.tracker.common.service.dto.PetTrackerResDto;
import com.tracker.dog.DogConstants;
import com.tracker.entity.EntityException;
import com.tracker.entity.Pet;
import com.tracker.search.NoDataFoundException;
import com.tracker.search.service.SearchTrackerDBService;
import com.tracker.search.service.dto.CatTrackerSearchResDto;
import com.tracker.search.service.dto.DogTrackerSearchResDto;
import com.tracker.search.service.dto.PetTrackerSearchResultsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SearchTrackerDBServiceImpl implements SearchTrackerDBService {

    private final SearchTrackerRepository repository;


    @Override
    public PetTrackerResDto searchTrackerById(final Long trackerId) throws NoDataFoundException, EntityException {
        Optional<Pet> pet = repository.findByTracker_Id(trackerId);
        if (pet.isPresent()) {
            Pet petEntity = pet.get();
            return getPetTrackerResDtoByPet(petEntity);
        }
        throw new NoDataFoundException("No Data found by TrackerID:" + trackerId);
    }

    @Override
    public PetTrackerSearchResultsDto searchByPetAndTrackerType(String pet, String trackerType) throws EntityException, NoDataFoundException {
        Optional<List<Pet>> petList = repository.findByPetTypeAndTracker_TrackerType(pet, trackerType);
        if (petList.isEmpty() || petList.get().isEmpty()) {
            throw new NoDataFoundException("No Data found for Pet Type:" + pet + " and tracker type:" + trackerType);
        }
        PetTrackerSearchResultsDto dtoList = new PetTrackerSearchResultsDto();
        for (Pet petEntity : petList.get()) {
            dtoList.getTrackerSearchModelList().add(getPetTrackerResDtoByPet(petEntity));
        }
        return dtoList;
    }

    @Override
    public Long getPetsOutsideOfZone(String pet, String trackerType) {
        return repository.countByPetTypeAndTracker_TrackerTypeAndTracker_InZone(pet, trackerType, false)
                .orElse(0L);
    }

    private PetTrackerResDto getPetTrackerResDtoByPet(Pet pet) throws EntityException {
        if (DogConstants.PET_TYPE_DOG.equals(pet.getPetType())) {
            DogTrackerSearchResDto dogTrackerDto = new DogTrackerSearchResDto(String.valueOf(pet.getTracker().getId()),
                    pet.getTracker().getTrackerType(), pet.getPetType(), pet.getTracker().isInZone(),
                    pet.getOwnerId());
            return dogTrackerDto;
        } else if (CatConstants.PET_TYPE_CAT.equals(pet.getPetType())) {
            CatTrackerSearchResDto catTrackerResDto = new CatTrackerSearchResDto(String.valueOf(pet.getTracker().getId()),
                    pet.getTracker().getTrackerType(), pet.getPetType(),
                    pet.getTracker().isLostTracker(), pet.getTracker().isInZone(),
                    pet.getOwnerId());
            return catTrackerResDto;
        }
        throw new EntityException("Invalid Pet Type retrieved from database:" + pet.getPetType());
    }


}
