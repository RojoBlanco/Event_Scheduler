package ru.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventScheduler eventScheduler = new EventSchedulerImpl();
    private static final int ADD_NEW_EVENT = 1;
    private static final int VIEW_LIST_EVENTS = 2;
    private static final int FILTER_BY_DATE = 3;
    private static final int DELETE_EVENT = 4;
    private static final int EXIT = 5;

    public static void main(String[] args) {
         int userChoice;
         printHelloText();

         do {
             System.out.print("Выберите действие (введите номер): ");
             userChoice = getUserChoice();
             scanner.nextLine();

             switch (userChoice){
                 case ADD_NEW_EVENT:
                     addNewEvent();
                     break;
                 case VIEW_LIST_EVENTS:
                     viewListEvents();
                     break;
                 case DELETE_EVENT:
                     deleteEvent();
                     break;
                 case FILTER_BY_DATE:
                     filterByDate();
                     break;
                 case EXIT:
                     System.out.println("До свидания!");
                     break;
                 default:
                     System.out.println("Такого действия нет");
                }
             } while (userChoice != EXIT);
         scanner.close();
    }

    private static void filterByDate() {
        System.out.print("Введите дату для фильтрации (в формате \"ГГГГ-ММ-ДД\"): ");
        String inputDate = scanner.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate, formatter);
            printEvents(eventScheduler.filterByDate(date));
        } catch (DateTimeParseException e) {
            System.out.println("Введина некоректная дата, перепроверьте формат ввода");
        }
    }
    private static void deleteEvent() {
        System.out.print("Введите номер события для удаления: ");
        try {
            int numberEvent = scanner.nextInt();
            eventScheduler.deleteEvent(numberEvent);
        } catch (InputMismatchException e) {
            System.out.println("Введино некоректное значение");
        } catch (IndexOutOfBoundsException e){
            System.out.println("Ошибка удаления события " + e.getMessage());
        }
        scanner.nextLine();
    }
    private static void viewListEvents(){
         printEvents(eventScheduler.viewAllEvents());
    }
    private static void addNewEvent() {
        System.out.print("Введите описание события: ");
        String description = scanner.nextLine();

        System.out.print("Введите дату и время начала события (в формате \"ГГГГ-ММ-ДД ЧЧ:ММ\"): ");
        String inputDateAndTime = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(inputDateAndTime, formatter);
            eventScheduler.addNewEvent(date, description);
            System.out.println("Событие успешно добавленно");
        } catch (DateTimeParseException e) {
            System.out.println("Введина некоректная дата и время, перепроверьте формат ввода");
        }

    }
    private static void printEvents(List<String> events){
        if(events.isEmpty()) {
            System.out.println("Событий нет");
        }
        else {
            for(String event: events) {
                System.out.println(event);
            }
        }
    }

    private static int getUserChoice(){
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
    }
    private static void printHelloText(){
        System.out.println("Добро пожаловать в приложение \"Планировщик событий\"!");
        System.out.println("1. Добавить событие");
        System.out.println("2. Просмотреть список событий");
        System.out.println("3. Фильтр по дате");
        System.out.println("4. Удалить событие");
        System.out.println("5. Выход");
    }
}