package com.tracker.search.presentation;

import com.tracker.cat.presentation.model.CatLostTrackerResModel;
import com.tracker.common.presentation.model.TrackerErrorResModel;
import com.tracker.entity.EntityException;
import com.tracker.search.NoDataFoundException;
import com.tracker.search.SearchTrackerException;
import com.tracker.search.presentation.model.PetTrackerSearchReqModel;
import com.tracker.search.presentation.model.TrackerModel;
import com.tracker.search.presentation.model.TrackerSearchResModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/search/")
@RestController
public class SearchTrackerController {

    private final SearchTrackerService searchTrackerService;

    @Operation(summary = "Search Tracker by Tracker Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(schema = @Schema(implementation = TrackerModel.class))),
            @ApiResponse(responseCode = "404", description = "Not Data found",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class)))
    })
    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public TrackerModel searchTracker(@Valid @NotNull(message = "TrackerId cannot be null") Long trackerId) throws NoDataFoundException, EntityException, SearchTrackerException {
        return searchTrackerService.searchByTrackerId(trackerId);
    }

    @Operation(summary = "Search Tracker by Pet Type and Tracker Type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(schema = @Schema(implementation = TrackerSearchResModel.class))),
            @ApiResponse(responseCode = "404", description = "Not Data Found",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class)))
    })
    @GetMapping(value = "pet-tracker-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public TrackerSearchResModel searchByPetAndTrackerType(@Valid PetTrackerSearchReqModel reqModel) throws EntityException, NoDataFoundException, SearchTrackerException {
        return searchTrackerService.searchByPetTypeTrackerType(reqModel.getPetType(), reqModel.getTrackerType());
    }

    @Operation(summary = "Get Number of Tracker Outside Power Zone by Pet Type and Tracker Type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "404", description = "Not Data Found",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class)))
    })
    @GetMapping("/outside-power-zone")
    public Long numberOfPetsOutsidePowerZone(@Valid PetTrackerSearchReqModel reqModel) throws EntityException {
        return searchTrackerService.getPetsOutSideOfZone(reqModel.getPetType(), reqModel.getTrackerType());
    }

}
