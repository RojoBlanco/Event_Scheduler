package ru.test;

import java.time.LocalDateTime;

public class Event {
    private final String eventDescription;
    private final LocalDateTime eventDate;

    public Event(LocalDateTime date, String event){
        eventDescription = event;
        eventDate = date;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return " " + eventDescription + " - " + eventDate.toLocalDate().toString() + " " + eventDate.toLocalTime().toString();
    }
}
