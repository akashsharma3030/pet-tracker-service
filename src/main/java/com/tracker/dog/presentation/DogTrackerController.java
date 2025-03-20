package com.tracker.dog.presentation;

import com.tracker.common.presentation.model.PetTrackerResponseModel;
import com.tracker.common.presentation.model.TrackerErrorResModel;
import com.tracker.dog.DogTrackerInternalException;
import com.tracker.dog.presentation.model.DogTrackerModel;
import com.tracker.dog.presentation.model.DogTrackerReqModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tracker/")
@RequiredArgsConstructor
@RestController
public class DogTrackerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DogTrackerService dogTrackerService;

    @Operation(summary = "Api to Register tracker for Dog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(schema = @Schema(implementation = PetTrackerResponseModel.class))),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class)))
    })
    @PostMapping("/dog/register")
    public PetTrackerResponseModel registerDogTracker(@Valid @RequestBody DogTrackerReqModel reqModel) throws DogTrackerException, DogTrackerInternalException {
        DogTrackerModel dogTrackerModel = new DogTrackerModel(reqModel.getOwnerId(), reqModel.getDogTrackerType(),
                true);
        logger.info("DogTrackerReqModel Validated successfully");
        return dogTrackerService.createDogTracker(dogTrackerModel);
    }


}
