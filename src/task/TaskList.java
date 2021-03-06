package task;

import persistance.textfile.TextTaskListDao;
import search.PropertyMap;
import util.Interval;
import util.Property;
import util.Repetition;
import util.Status;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


//ONLY tasks that are NOT a repetition are added to the taskList

public class TaskList {



    private List<Task> taskList = new ArrayList<>();

    private TextTaskListDao textFileWriter = new TextTaskListDao(new File("TaskList"));


    public void addTask(Task task) {
        if (!task.getIsRepetition()) {
            this.taskList.add(task);
        } else {
            Repetition rep = task.getRepetition();
            String description = task.getDescription();
            LocalDate firstDueDate = task.getDueDate();
            Interval interval = rep.getInterval();
            for (int i = 0; i < rep.getQuantity(); i++) {
                LocalDate dueDate = task.getRepetition().addInterval(firstDueDate, interval, i);
                this.taskList.add(new Task(description, dueDate, task.getStatus()));
            }
        }
    }

    // remove the task
    public void removeTask(Task task) {
        this.taskList.remove(task);
    }

    // get a task by some property
    public Task getTask(String description, LocalDate dueDate, Status status) {
        Task task = null;
        for (Task t : this.taskList) {
            if (t.getDescription().equals(description) && t.getDueDate().equals(dueDate) &&
                    t.getStatus().equals(status)) {
                task = t;
                break;
            }
        }
        return task;
    }

    // removes all tasks that have status DONE
    public void cleanTaskList() {
        for (Iterator<Task> iterator = this.taskList.iterator(); iterator.hasNext(); ) {
            Task task = iterator.next();
            if (task.getStatus() == Status.CLOSED) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        String taskListString = "";
        for (Task task : this.taskList) taskListString += task + "\n";
        return taskListString;
    }

    public String byStatus(){
        String taskListString = "";
        for (Task task : this.taskList) taskListString += task.byStatus() + "\n";
        return taskListString;
    }

    public String byDueDate(){
        String taskListString = "";
        for (Task task : this.taskList) taskListString += task.byDueDate() + "\n";
        return taskListString;
    }
    public String byID(){
        String taskListString = "";
        for (Task task : this.taskList) taskListString += task.byID() + "\n";
        return taskListString;
    }

    public void saveToFile() {
        try {
            this.textFileWriter.save(this.taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Will do the following checks:
     * - for DESCRIPTION: get all the tasks which contain the value in the description
     * - for DUE_DATE: get all the tasks where the dueDate is BEFORE the dueDateValue
     * - for STATUS: get all tasks where the status is equal to statusValue
     */
    public <Key, Value> List<Task> searchTasksByProperty(PropertyMap<Key, Value> propertyMap) {
        List<Task> foundTasks = new ArrayList<>();
        for (Iterator<Task> iterator = this.taskList.iterator(); iterator.hasNext(); ) {
            Task task = iterator.next();
            if (propertyMap.getKey().equals(Property.DESCRIPTION)) {
                String searchString = (String) propertyMap.getValue();
                if (task.getDescription().contains(searchString)) foundTasks.add(task);
            } else if (propertyMap.getKey().equals(Property.DUE_DATE)) {
                LocalDate lastDueDate = (LocalDate) propertyMap.getValue();
                if (task.getDueDate().isBefore(lastDueDate)) foundTasks.add(task);
            } else if (propertyMap.getKey().equals(Property.STATUS)) {
                Status requiredStatus = (Status) propertyMap.getValue();
                if (task.getStatus().equals(requiredStatus)) foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    // TODO: add List<Property> as parameter, then make a stream of Comparators 'thenComparing()'
    // will sort by description (String), by dueDate (ascending), by status (ordinal) or by ID (ascending)
    public void sortByPropertyList(ArrayList<Property> propertyList) {
        if (propertyList != null) {
            Comparator comparator =
                    propertyList.get(0).equals(Property.DESCRIPTION) ? Comparator.comparing(Task::getDescriptionToLowerCase) :
                            propertyList.get(0).equals(Property.DUE_DATE) ? Comparator.comparing(Task::getDueDate) :
                                    propertyList.get(0).equals(Property.STATUS) ? Comparator.comparing(Task::getStatus) :
                                            propertyList.get(0).equals(Property.ID) ? Comparator.comparing(Task::getId) : null;
            for (int i=1 ; i<propertyList.size() ; i++){
                Comparator comparator1 =
                        propertyList.get(i).equals(Property.DESCRIPTION) ? Comparator.comparing(Task::getDescriptionToLowerCase) :
                                propertyList.get(i).equals(Property.DUE_DATE) ? Comparator.comparing(Task::getDueDate) :
                                        propertyList.get(i).equals(Property.STATUS) ? Comparator.comparing(Task::getStatus) :
                                                propertyList.get(i).equals(Property.ID) ? Comparator.comparing(Task::getId) : null;

                comparator = comparator.thenComparing(comparator1);
            }
            this.taskList.sort(comparator);
        }
    }
}
