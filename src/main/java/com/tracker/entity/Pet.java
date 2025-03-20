package com.tracker.entity;

import com.tracker.cat.CatConstants;
import com.tracker.dog.DogConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table
@Setter
@NoArgsConstructor
public class Pet {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Integer ownerId;

    private String petType;
    @OneToOne(cascade = CascadeType.ALL)
    private Tracker tracker;

    public Pet(Integer ownerId, String petType, String trackingType, boolean inZone) throws EntityException {
        validatePetTracker(ownerId, petType, trackingType);
        this.petType = petType;
        this.ownerId = ownerId;
        this.tracker = new Tracker(trackingType, inZone);
    }

    public Pet(Integer ownerId, String petType, String trackingType, boolean inZone,
               boolean lostTracker) throws EntityException {
        validatePetTracker(ownerId, petType, trackingType);
        this.petType = petType;
        this.ownerId = ownerId;
        this.tracker = new Tracker(trackingType, inZone, lostTracker);
    }

    private void validatePetTracker(Integer ownerId, String petType, String trackingType) throws EntityException {
        if (ownerId == null) {
            throw new EntityException("Owner ID cannot be null");
        } else if (!(DogConstants.PET_TYPE_DOG.equals(petType) || CatConstants.PET_TYPE_CAT.equals(petType))) {
            throw new EntityException("PET type should be either DOG or CAT instead of " + petType);
        } else if (DogConstants.PET_TYPE_DOG.equals(petType) &&
                !DogConstants.DOG_TRACKER_TYPE.contains(trackingType)) {
            throw new EntityException("For Pet Dog invalid Tracker type:" + tracker.getTrackerType() +
                    "  provided");
        } else if (CatConstants.PET_TYPE_CAT.equals(petType) &&
                !CatConstants.CAT_TRACKER_TYPE.contains(trackingType)) {
            throw new EntityException("For Pet Cat invalid Tracker type:" + tracker.getTrackerType() +
                    "  provided");
        }
    }

}
