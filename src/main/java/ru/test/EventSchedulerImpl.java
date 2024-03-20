package ru.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
* Реализация планировщика событий
* */
public class EventSchedulerImpl implements EventScheduler {
    private final List<Event> events;

    public EventSchedulerImpl() {
        events = new ArrayList<>();
    }
    // Добавление нового элемета в планировщик событий
    public void addNewEvent(LocalDateTime event, String eventDescription) {
        events.add(new Event(event, eventDescription));
    }
    // Возвращение всех запланированный событий
    public List<String> viewAllEvents() {
        List<String> allEventsList = new ArrayList<>();
        for(int i = 0; i < events.size(); i++){
            var event =  events.get(i);
            allEventsList.add((i + 1) + event.toString());
        }
        return allEventsList;
    }
    // Удаления события
    public void deleteEvent(int numberEvent) {
        if(numberEvent <= 0 || numberEvent > events.size()){
            throw new IndexOutOfBoundsException(numberEvent + ": Нет такого события");
        }
        events.remove(numberEvent - 1);
    }
    // Возвращение событий отфильрованных по дате
    public List<String> filterByDate(LocalDate date) {
        int k = 0;
        List<String> filteredDate = new ArrayList<>();
        for (Event event : events) {
            if (date.equals(event.getEventDate().toLocalDate())) {
               filteredDate.add(++k + event.toString());
            }
        }
        return filteredDate;
    }
    // Возвращение размера
    public int getSize() {
        return events.size();
    }
}