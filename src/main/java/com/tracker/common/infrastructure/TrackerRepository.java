package com.tracker.common.infrastructure;

import com.tracker.entity.Tracker;
import org.springframework.data.repository.CrudRepository;

public interface TrackerRepository extends CrudRepository<Tracker, Long> {

}
