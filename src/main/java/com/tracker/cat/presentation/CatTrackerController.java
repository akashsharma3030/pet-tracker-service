package com.tracker.cat.presentation;

import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.cat.presentation.model.CatLostTrackerReqModel;
import com.tracker.cat.presentation.model.CatLostTrackerResModel;
import com.tracker.cat.presentation.model.CatTrackerModel;
import com.tracker.cat.presentation.model.CatTrackerReqModel;
import com.tracker.common.presentation.model.PetTrackerResponseModel;
import com.tracker.common.presentation.model.TrackerErrorResModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/tracker/")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CatTrackerController {

    private final CatTrackerService catTrackerService;


    @Operation(summary = "Api to Register tracker for Cat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(schema = @Schema(implementation = PetTrackerResponseModel.class))),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class)))
    })
    @PostMapping("/cat/register")
    public PetTrackerResponseModel registerCatTracker(final @RequestBody @Valid CatTrackerReqModel reqModel) throws CatTrackerException, CatTrackerInternalException {
        CatTrackerModel catTrackerModel = new CatTrackerModel(reqModel.getOwnerId(), reqModel.getCatTrackerType(),
                true, false);
        log.info("CatTrackerReqModel Validated successfully");
        return catTrackerService.createCatTracker(catTrackerModel);
    }

    @Operation(summary = "Update lost tracker status for Cat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(schema = @Schema(implementation = CatLostTrackerResModel.class))),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class)))
    })
    @PatchMapping("/cat/lost-tracker")
    public CatLostTrackerResModel lostTracker(final @RequestBody @Valid CatLostTrackerReqModel catLostTrackerReqModel) throws CatTrackerException {
        return catTrackerService.updateLostTracker(catLostTrackerReqModel);
    }

}
