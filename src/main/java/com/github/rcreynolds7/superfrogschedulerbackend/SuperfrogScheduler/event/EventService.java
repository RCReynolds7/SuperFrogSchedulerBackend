package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.event;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return this.eventRepository.save(event);
    }

    public Event updateEvent(Integer id, Event eventDetails) {
        Event event = this.eventRepository
                        .findById(id)
                        .map(oldEvent -> {
                            oldEvent.setTitle(eventDetails.getTitle());
                            oldEvent.setStartDate(eventDetails.getStartDate());
                            oldEvent.setEndDate(eventDetails.getEndDate());
                            oldEvent.setRecurrenceStartDate(eventDetails.getRecurrenceStartDate());
                            oldEvent.setRecurrenceEndDate(eventDetails.getRecurrenceEndDate());
                            return oldEvent;
                        })
                        .orElseThrow(() -> new ObjectNotFoundException("event", id));
        return eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        this.eventRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("event", id));
        this.eventRepository.deleteById(id);
    }

    public Event findById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Event", id));
    }
}
