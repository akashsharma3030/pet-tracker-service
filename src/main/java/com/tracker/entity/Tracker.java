package com.tracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Table
@Setter
@NoArgsConstructor
public class Tracker {

    private final static List<String> TRACKER_TYPE_LIST = new ArrayList<>(Arrays.asList("SMALL", "MEDIUM", "BIG"));
    @Id
    private Long Id;

    @Column
    private String trackerType;

    @Column
    private boolean inZone;

    @Column
    private boolean lostTracker;

    public Tracker(String trackerType, boolean inZone, Boolean lostTracker) {
        Id = Instant.now().toEpochMilli();
        this.trackerType = trackerType;
        this.inZone = inZone;
        this.lostTracker = lostTracker;

    }

    public Tracker(String trackerType, boolean inZone) {
        Id = Math.abs(new SecureRandom().nextLong());
        this.trackerType = trackerType;
        this.inZone = inZone;
        this.lostTracker = false;
    }
}
