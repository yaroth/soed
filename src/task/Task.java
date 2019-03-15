package task;

import util.Repetition;
import util.Status;

import java.time.LocalDate;

public class Task {

    /*might use this as an ID*/
    private static int count = 0;



    private int id = 0;
    private String description = "";
    private LocalDate dueDate = LocalDate.now(); // default is now()!
    private Status status = Status.OPEN;
    private Boolean isRepetition = false;
    private Repetition repetition = null;

    public Task(String description, LocalDate date, Status status) {
        this.id = count;
        count++;
        this.description = description;
        this.dueDate = date;
        this.status = status;
    }

    public Task(Repetition repetition, String description, Status status) {
        this.isRepetition = true;
        this.repetition = repetition;
        this.description = description;
        this.status = status;
    }

    public Task() {
        this.id = count;
        count++;
        this.isRepetition = false;
        this.repetition = null;
        this.description = null;
        this.dueDate = LocalDate.now();
        this.status = Status.OPEN;
    }


    public String getDescription() {
        return description;
    }

    public String getDescriptionToLowerCase() {
        return description.toLowerCase();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getIsRepetition() {
        return isRepetition;
    }

    public Repetition getRepetition() {
        return repetition;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return description + ", " + status + ", " + dueDate + "(ID: " + id +")";
    }
}
