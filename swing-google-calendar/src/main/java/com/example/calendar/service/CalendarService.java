package com.example.calendar.service;

import com.example.calendar.model.CalendarEvent;

import java.time.LocalDate;
import java.util.*;

/**
 * 后端服务：负责事件数据的增删改查。
 * 这里使用内存实现，便于初学者理解 MVC 与服务层解耦。
 */
public class CalendarService {
    private final Map<LocalDate, List<CalendarEvent>> eventStore = new HashMap<>();

    public List<CalendarEvent> getEventsByDate(LocalDate date) {
        return new ArrayList<>(eventStore.getOrDefault(date, Collections.emptyList()));
    }

    public void addEvent(CalendarEvent event) {
        eventStore.computeIfAbsent(event.getDate(), key -> new ArrayList<>()).add(event);
        sortEvents(event.getDate());
    }

    public void updateEvent(LocalDate oldDate, CalendarEvent updatedEvent) {
        deleteEvent(oldDate, updatedEvent.getId());
        addEvent(updatedEvent);
    }

    public void deleteEvent(LocalDate date, String eventId) {
        List<CalendarEvent> events = eventStore.getOrDefault(date, new ArrayList<>());
        events.removeIf(e -> e.getId().equals(eventId));
        if (events.isEmpty()) {
            eventStore.remove(date);
        }
    }

    private void sortEvents(LocalDate date) {
        eventStore.get(date).sort(Comparator.comparing(CalendarEvent::getStartTime));
    }
}
