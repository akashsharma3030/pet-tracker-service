package com.tracker.search.infrastructure;

import com.tracker.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SearchTrackerRepository extends CrudRepository<Pet, Long>,
        JpaRepository<Pet, Long> {

    Optional<List<Pet>> findByPetTypeAndTracker_TrackerType(String petType, String trackerType);

    Optional<Pet> findByTracker_Id(Long trackerId);

    Optional<Long> countByPetTypeAndTracker_TrackerTypeAndTracker_InZone(String petType, String trackerType, boolean inZone);


}
