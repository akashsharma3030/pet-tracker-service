package com.tracker.search.presentation.model.validator;

import com.tracker.cat.CatConstants;
import com.tracker.common.presentation.model.PetType;
import com.tracker.dog.DogConstants;
import com.tracker.search.presentation.model.PetTrackerSearchReqModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PetTrackerTypeValidator implements ConstraintValidator<PetTrackerTypeValidation, PetTrackerSearchReqModel> {

    @Override
    public boolean isValid(PetTrackerSearchReqModel value, ConstraintValidatorContext context) {
        if (PetType.CAT.equals(value.getPetType()) && CatConstants.CAT_TRACKER_TYPE.contains(value.getTrackerType().name())) {
            return true;
        } else if (PetType.DOG.equals(value.getPetType()) &&
                DogConstants.DOG_TRACKER_TYPE.contains(value.getTrackerType().name())) {
            return true;
        }
        String petType = value.getPetType() != null ? value.getPetType().name() : "";
        String trackerType = value.getTrackerType() != null ? value.getTrackerType().name() : "";
        //   context.buildConstraintViolationWithTemplate("Invalid Pet Type"+petType +
        //       "and Tracker Type provided:"+trackerType)
        //      .addConstraintViolation();
        return false;
    }

}
