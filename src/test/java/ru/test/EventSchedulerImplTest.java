package ru.test;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventSchedulerImplTest {
    private EventSchedulerImpl eventScheduler;
    @Before
    public void setUp() {
        eventScheduler = new EventSchedulerImpl();
    }

    @Test
    public void addNewEvent() {
        LocalDateTime eventDateTime = LocalDateTime.of(2024, 3, 20, 10, 0);
        eventScheduler.addNewEvent(eventDateTime, "Тестовый пример");
        assertEquals(1, eventScheduler.getSize());
    }

    @Test
    public void viewAllEvents() {
        LocalDateTime eventDateTime1 = LocalDateTime.of(2024, 3, 20, 10, 0);
        LocalDateTime eventDateTime2 = LocalDateTime.of(2024, 3, 21, 12, 0);
        eventScheduler.addNewEvent(eventDateTime1, "Тестовый пример 1");
        eventScheduler.addNewEvent(eventDateTime2, "Тестовый пример 2");

        List<String> allEvents = eventScheduler.viewAllEvents();
        assertEquals(2, allEvents.size());
    }

    @Test
    public void deleteEvent() {
        LocalDateTime eventDateTime = LocalDateTime.of(2024, 3, 20, 10, 0);
        eventScheduler.addNewEvent(eventDateTime, "Тестовый пример");

        eventScheduler.deleteEvent(1);
        assertEquals(0, eventScheduler.getSize());
    }
    @Test
    public void testAddNewEvent() {
        LocalDateTime eventDateTime = LocalDateTime.now();
        String eventDescription = "Тестовый пример";
        eventScheduler.addNewEvent(eventDateTime, eventDescription);
        assertEquals(1, eventScheduler.getSize());
    }

    @Test
    public void testDeleteEvent() {
        LocalDateTime eventDateTime = LocalDateTime.now();
        String eventDescription = "Тестовый пример";
        eventScheduler.addNewEvent(eventDateTime, eventDescription);
        int initialSize = eventScheduler.getSize();
        eventScheduler.deleteEvent(1);
        assertEquals(initialSize - 1, eventScheduler.getSize());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteEventWithInvalidIndex() {
        eventScheduler.deleteEvent(1);
    }

    @Test
    public void filterByDate() {
        LocalDateTime eventDateTime1 = LocalDateTime.of(2024, 3, 20, 10, 0);
        LocalDateTime eventDateTime2 = LocalDateTime.of(2024, 3, 21, 12, 0);
        eventScheduler.addNewEvent(eventDateTime1, "Тестовый пример 1");
        eventScheduler.addNewEvent(eventDateTime2, "Тестовый пример 2");

        List<String> filteredEvents = eventScheduler.filterByDate(LocalDate.of(2024, 3, 20));
        assertEquals(1, filteredEvents.size());
    }


}