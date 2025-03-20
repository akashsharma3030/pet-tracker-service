package com.tracker.cat.infrastructure;

import com.tracker.entity.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CatTrackerRepository extends CrudRepository<Pet, Long> {
    Optional<Pet> findByTracker_Id(Long trackerId);
}
