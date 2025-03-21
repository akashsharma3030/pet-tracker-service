package com.tracker.common.service;

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
public class TrackerServiceImplTest {

    @Mock
    private TrackerDBService trackerDBService;

    @InjectMocks
    private TrackerServiceImpl trackerService;

    @Test
    void updateInZoneTrackerStatus_Success() throws NoDataFoundException {
        // Act
        TrackerResModel response = trackerService.updateInZoneTrackerStatus(1L, true);

        // Assert
        assertNotNull(response);
        assertEquals("Zone status for tracker Updated successfully", response.getMessage());
        verify(trackerDBService).updateOutOfZone(1L, true);
    }

    @Test
    void updateInZoneTrackerStatus_NoDataFound() throws NoDataFoundException {
        // Arrange
        doThrow(new NoDataFoundException("No tracker data found")).when(trackerDBService).updateOutOfZone(anyLong(), anyBoolean());

        // Act & Assert
        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> {
            trackerService.updateInZoneTrackerStatus(1L, true);
        });
        assertEquals("No tracker data found", exception.getMessage());
        verify(trackerDBService).updateOutOfZone(1L, true);
    }
}