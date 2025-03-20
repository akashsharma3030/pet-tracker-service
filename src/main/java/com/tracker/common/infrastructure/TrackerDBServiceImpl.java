package com.tracker.common.infrastructure;

import com.tracker.common.service.TrackerDBService;
import com.tracker.entity.Tracker;
import com.tracker.search.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackerDBServiceImpl implements TrackerDBService {

    private final TrackerRepository repository;

    @Override
    public void updateOutOfZone(final Long trackerId, final boolean inZone) throws NoDataFoundException {
        Optional<Tracker> tracker = repository.findById(trackerId);
        if (tracker.isPresent()) {
            Tracker trackerEntity = tracker.get();
            trackerEntity.setInZone(inZone);
            repository.save(trackerEntity);
            log.info("Tracker with id:" + trackerId + " updated with status:" + inZone);
        } else {
            throw new NoDataFoundException("No Tracker found for TaskId:" + trackerId);
        }

    }
}
