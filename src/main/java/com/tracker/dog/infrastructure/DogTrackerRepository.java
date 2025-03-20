package com.tracker.dog.infrastructure;

import com.tracker.entity.Pet;
import org.springframework.data.repository.CrudRepository;

public interface DogTrackerRepository extends CrudRepository<Pet, Long> {

}
