package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    EventRepository eventRepository;

    @InjectMocks
    EventService eventService;

    List<Event> events;

    @BeforeEach
    void setUp() {
        Event e1 = new Event();
        e1.setId(1);
        e1.setTitle("Event 1");
        e1.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        e1.setEndDate(LocalDateTime.parse("2022-12-27T10:50:00"));
        
        Event e2 = new Event();
        e2.setId(2);
        e2.setTitle("Event 2");
        e2.setStartDate(LocalDateTime.parse("2023-01-03T05:00:00"));
        e2.setEndDate(LocalDateTime.parse("2023-02-03T06:00:00"));
        e2.setRecurring(true);
        e2.setRecurrenceStartDate(LocalDateTime.parse("2023-01-03T05:00:00"));
        e2.setRecurrenceEndDate(LocalDateTime.parse("2023-02-03T06:00:00"));


        this.events = new ArrayList<>();
        this.events.add(e1);
        this.events.add(e2);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testSaveSuccess() {
        // Given
        Event newEvent = new Event();
        newEvent.setTitle("New Event");
        newEvent.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        newEvent.setEndDate(LocalDateTime.parse("2022-12-27T10:50:00"));

        given(eventRepository.save(newEvent)).willReturn(newEvent);

        // When
        Event savedEvent = eventService.createEvent(newEvent);

        // Then
        assertThat(savedEvent.getId()).isEqualTo(newEvent.getId());
        assertThat(savedEvent.getTitle()).isEqualTo(newEvent.getTitle());
        assertThat(savedEvent.getStartDate()).isEqualTo(newEvent.getStartDate());
        assertThat(savedEvent.getEndDate()).isEqualTo(newEvent.getEndDate());
        verify(eventRepository, times(1)).save(newEvent);
    }

    @Test
    void testUpdateSuccess() {
        // Given
        Event oldEvent = new Event();
        oldEvent.setId(1);
        oldEvent.setTitle("Event 1");
        oldEvent.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        oldEvent.setEndDate(LocalDateTime.parse("2022-12-27T10:50:00"));

        Event update = new Event();
        update.setId(1);
        update.setTitle("New Title");
        update.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        update.setEndDate(LocalDateTime.parse("2022-12-27T10:50:00"));

        given(eventRepository.findById(1)).willReturn(java.util.Optional.of(oldEvent));
        given(eventRepository.save(oldEvent)).willReturn(oldEvent);

        // When
        Event updatedEvent = eventService.updateEvent(1, update);

        // Then
        assertThat(updatedEvent.getId()).isEqualTo(update.getId());
        assertThat(updatedEvent.getTitle()).isEqualTo(update.getTitle());
        assertThat(updatedEvent.getStartDate()).isEqualTo(update.getStartDate());
        assertThat(updatedEvent.getEndDate()).isEqualTo(update.getEndDate());
        verify(eventRepository, times(1)).findById(1);
        verify(eventRepository, times(1)).save(oldEvent);
    }

    @Test
    void testUpdateNotFound() {
        // Given
        Event update = new Event();
        update.setId(3);
        update.setTitle("New Title");
        update.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        update.setEndDate(LocalDateTime.parse("2022-12-27T10:50:00"));

        given(eventRepository.findById(3)).willReturn(java.util.Optional.empty());

        // When
        assertThrows(ObjectNotFoundException.class, () -> {
            eventService.updateEvent(3, update);
        });

        // Then
        verify(eventRepository, times(1)).findById(3);
    }

    @Test
    void testDeleteSuccess() {
        // Given
        Event event = new Event();
        event.setId(1);
        event.setTitle("Event 1");
        event.setStartDate(LocalDateTime.parse("2022-12-25T10:30:00"));
        event.setEndDate(LocalDateTime.parse("2022-12-27T10:50:00"));

        given(eventRepository.findById(1)).willReturn(java.util.Optional.of(event));

        // When
        eventService.deleteEvent(1);

        // Then
        verify(eventRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteNotFound() {
        // Given
        given(eventRepository.findById(3)).willReturn(java.util.Optional.empty());

        // When
        assertThrows(ObjectNotFoundException.class, () -> {
            eventService.deleteEvent(3);
        });

        // Then
        verify(eventRepository, times(1)).findById(3);
    }
}
