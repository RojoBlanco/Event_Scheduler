package ru.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventScheduler {
    void addNewEvent(LocalDateTime event, String eventDescription);
    void deleteEvent(int numberEvent);
    List<String> filterByDate(LocalDate date);
    List<String> viewAllEvents();
    int getSize();
}