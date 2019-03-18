import search.PropertyMap;
import task.Task;
import task.TaskList;
import util.Property;
import util.Interval;
import util.Repetition;
import util.Status;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        TaskList taskList = new TaskList();

        Task task = new Task("buy milk", LocalDate.of(2019, 06, 23), Status.OPEN);
        Task task1 = new Task("clean desk", LocalDate.of(2019, 03, 10), Status.OPEN);

        Repetition weekly5Repetition = new Repetition(LocalDate.of(2019, 3, 11), Interval.WEEK, 3);
        Task task2 = new Task(weekly5Repetition, "some repetitive task", Status.OPEN);

        Task task3 = new Task("through away milk container", LocalDate.of(2019, 04, 12), Status.CLOSED);

        Repetition weeklyEmptyTrash = new Repetition(LocalDate.of(2019, 3, 2), Interval.WEEK, 5);
        Task task4 = new Task(weeklyEmptyTrash, "Empty trash bin", Status.OPEN);
        Task task5 = new Task("Empty trash bin", LocalDate.of(2019, 3, 15), Status.OPEN);

//        task.setStatus(Status.CLOSED);
        task1.setStatus(Status.CLOSED);

        taskList.addTask(task);
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.addTask(task4);
        taskList.addTask(task5);

        Task retrievedTask = taskList.getTask("buy milk", LocalDate.of(2019, 06, 23), Status.OPEN);
        if (retrievedTask != null) retrievedTask.setStatus(Status.CLOSED);
//        System.out.println(taskList);
//        taskList.removeTask(task2);

        System.out.println("*********************** Full TaskList ***********************");
        System.out.print(taskList);
        System.out.println("*************************************************************");
        System.out.println();

        PropertyMap<Property, String> descriptionPropertyMap = new PropertyMap<>(Property.DESCRIPTION, "milk");
        System.out.println(descriptionPropertyMap + " " + taskList.searchTasksByProperty(descriptionPropertyMap));

        PropertyMap<Property, Status> closedPropertyMap = new PropertyMap<>(Property.STATUS, Status.CLOSED);
        System.out.println(closedPropertyMap + " " + taskList.searchTasksByProperty(closedPropertyMap));

        PropertyMap<Property, LocalDate> before31May2019PropertyMap = new PropertyMap<>(Property.DUE_DATE, LocalDate.of(2019, 5, 31));
        System.out.println(before31May2019PropertyMap + " " + taskList.searchTasksByProperty(before31May2019PropertyMap));


        ArrayList<Property> sortingPropertiesList = new ArrayList<>();
        sortingPropertiesList.add(Property.DESCRIPTION);
        taskList.sortByPropertyList(sortingPropertiesList);
        System.out.println();
        System.out.println("Sort by description: ");
        System.out.println(taskList);

        // first sorting order is 'description', now add 'due date'
        sortingPropertiesList.add(Property.DUE_DATE);
        taskList.sortByPropertyList(sortingPropertiesList);
        System.out.println("Sort by description, then dueDate: ");
        System.out.println(taskList);

        sortingPropertiesList.add(Property.STATUS);
        taskList.sortByPropertyList(sortingPropertiesList);
        System.out.println("Sort by description, then dueDate, then status: ");
        System.out.println(taskList);

        sortingPropertiesList.clear();
        sortingPropertiesList.add(Property.STATUS);
        taskList.sortByPropertyList(sortingPropertiesList);
        System.out.println("Sort by status: ");
        System.out.println(taskList.byStatus());

        sortingPropertiesList.add(Property.DUE_DATE);
        taskList.sortByPropertyList(sortingPropertiesList);
        System.out.println("Sort by status, then by date: \n" + taskList.byStatus());

        sortingPropertiesList.clear();
        sortingPropertiesList.add(Property.DUE_DATE);
        taskList.sortByPropertyList(sortingPropertiesList);
        System.out.println("Sort by due date: ");
        System.out.println(taskList.byDueDate());

//        taskList.cleanTaskList();

        sortingPropertiesList.clear();
        sortingPropertiesList.add(Property.ID);
        taskList.sortByPropertyList(sortingPropertiesList);
        System.out.println("Sort by ID: \n" + taskList.byID());
        taskList.saveToFile();
    }
}
