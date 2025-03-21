package com.tracker.common.infrastructure;

import com.tracker.entity.Tracker;
import com.tracker.search.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrackerDBServiceImplTest {

    @Mock
    private TrackerRepository repository;

    @InjectMocks
    private TrackerDBServiceImpl trackerDBService;

    @Test
    void updateOutOfZone_Success() throws NoDataFoundException {
        // Arrange
        Tracker tracker = new Tracker();
        tracker.setId(1L);
        tracker.setInZone(true);
        when(repository.findById(anyLong())).thenReturn(Optional.of(tracker));

        // Act
        trackerDBService.updateOutOfZone(1L, false);

        // Assert
        verify(repository).findById(1L);
        verify(repository).save(tracker);
    }

    @Test
    void updateOutOfZone_NoDataFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> {
            trackerDBService.updateOutOfZone(1L, false);
        });
        verify(repository).findById(1L);
        verify(repository, never()).save(any(Tracker.class));
    }
}