package com.tracker.common.presentation;

import com.tracker.cat.CatTrackerException;
import com.tracker.common.presentation.model.TrackerErrorResModel;
import com.tracker.common.presentation.model.TrackerResModel;
import com.tracker.search.NoDataFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tracker/")
public class TrackerController {

    private final TrackerService trackerService;

    @Operation(summary = "Update zone tracker status True InZone False Out of Zone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated zone status",
                    content = @Content(schema = @Schema(implementation = TrackerResModel.class))),
            @ApiResponse(responseCode = "404", description = "No tracker data found to update the zone status",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = TrackerErrorResModel.class)))
    })
    @PatchMapping("/pet/zone")
    public TrackerResModel updateZoneTrackerStatus(@Valid @NotNull(message = "TrackerId cannot be Null") Long trackerId,
                                                   @Valid @NotNull(message = "Out of Zone flag should not be null") Boolean inZone) throws CatTrackerException, NoDataFoundException {
        return trackerService.updateInZoneTrackerStatus(trackerId, inZone);
    }

}
