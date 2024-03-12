package com.example.repository;

import com.example.entity.TimeSlot;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TimeSlotRepositoryTest {
    @Mock
    TimeSlotRepository timeSlotRepository;

    @Test
    void addTimeSlot() {
        // Given
        TimeSlot timeSlot = TimeSlot.builder().id(1).startTime("9:00 AM").endTime("12:00 PM")
                .available(true).build();
        // When
        when(timeSlotRepository.save(timeSlot)).thenReturn(timeSlot);

        // Then
        TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
        assertThat(savedTimeSlot).isNotNull();
        assertThat(savedTimeSlot).isEqualTo(timeSlot);
        System.out.println(timeSlot);
    }

    @Test
    void updateTimeSlot() {
        // Given
        TimeSlot originalTimeSlot = TimeSlot.builder().id(1).startTime("9:00 AM").endTime("12:00 PM")
                .available(true).build();
        TimeSlot timeSlotToUpdate = TimeSlot.builder().id(1).startTime("9:00 AM").endTime("12:00 PM")
                .available(false).build();
        timeSlotRepository.save(originalTimeSlot);
        // When
        when(timeSlotRepository.findById(timeSlotToUpdate.getId())).thenReturn(Optional.of(originalTimeSlot));
        when(timeSlotRepository.save(originalTimeSlot)).thenReturn(timeSlotToUpdate);


        // Then
        TimeSlot retrievedTimeSlot = timeSlotRepository.findById(timeSlotToUpdate.getId()).orElseThrow();
        retrievedTimeSlot.setId(timeSlotToUpdate.getId());
        retrievedTimeSlot.setStartTime(timeSlotToUpdate.getStartTime());
        retrievedTimeSlot.setEndTime(timeSlotToUpdate.getEndTime());
        retrievedTimeSlot.setAvailable(timeSlotToUpdate.isAvailable());
        TimeSlot updatedTimeSlot = timeSlotRepository.save(retrievedTimeSlot);
        assertThat(updatedTimeSlot).isNotNull();
        assertThat(updatedTimeSlot.getId()).isEqualTo(timeSlotToUpdate.getId());
        System.out.println(updatedTimeSlot);
    }

    @Test
    void deleteTimeSlot() {
        // Given
        TimeSlot timeSlot = TimeSlot.builder().id(1).startTime("9:00 AM").endTime("12:00 PM")
                .available(true).build();

        // When
        when(timeSlotRepository.findById(timeSlot.getId())).thenReturn(Optional.of(timeSlot));

        // Then
        timeSlotRepository.deleteById(timeSlot.getId());
        verify(timeSlotRepository, times(1)).deleteById(timeSlot.getId());
    }
}