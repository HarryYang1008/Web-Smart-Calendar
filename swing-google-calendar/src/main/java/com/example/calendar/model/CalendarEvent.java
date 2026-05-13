package com.example.calendar.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * 日程实体类：用于表示一个完整的日历事件。
 */
public class CalendarEvent {
    private final String id;
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String description;

    public CalendarEvent(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String location, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String toDisplayText() {
        return String.format("%s %s-%s | %s", title, startTime, endTime, location == null ? "" : location);
    }
}
