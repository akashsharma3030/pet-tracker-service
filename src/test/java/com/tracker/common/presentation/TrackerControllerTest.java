package com.tracker.common.presentation;

import com.tracker.cat.CatTrackerException;
import com.tracker.common.presentation.model.TrackerResModel;
import com.tracker.search.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrackerControllerTest {

    @Mock
    private TrackerService trackerService;

    @InjectMocks
    private TrackerController trackerController;

    @Test
    void updateZoneTrackerStatus_Success() throws CatTrackerException, NoDataFoundException {
        // Arrange
        TrackerResModel expectedResponse = new TrackerResModel("Zone status updated successfully");
        when(trackerService.updateInZoneTrackerStatus(anyLong(), anyBoolean())).thenReturn(expectedResponse);

        // Act
        TrackerResModel actualResponse = trackerController.updateZoneTrackerStatus(1L, true);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(trackerService).updateInZoneTrackerStatus(1L, true);
    }

    @Test
    void updateZoneTrackerStatus_NoDataFound() throws CatTrackerException, NoDataFoundException {
        // Arrange
        when(trackerService.updateInZoneTrackerStatus(anyLong(), anyBoolean())).thenThrow(new NoDataFoundException("No tracker data found"));

        // Act & Assert
        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> {
            trackerController.updateZoneTrackerStatus(1L, true);
        });
        assertEquals("No tracker data found", exception.getMessage());
        verify(trackerService).updateInZoneTrackerStatus(1L, true);
    }

}