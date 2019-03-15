package util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class Repetition {

    private LocalDate firstDueDate = LocalDate.now();

    // what is the repetition interval?
    private Interval interval = Interval.WEEK;  //interval default is 1 week

    // how many times does the repetition need to be repeated?
    private int quantity = 0;

    public Repetition(LocalDate firstDueDate, Interval interval, int quantity) {
        this.firstDueDate = firstDueDate;
        this.interval = interval;
        this.quantity = quantity;
    }

    public Repetition() {
        this.firstDueDate = LocalDate.now();
        this.interval = Interval.WEEK;
        this.quantity = 0;
    }

    public LocalDate getFirstDueDate() {
        return firstDueDate;
    }

    public void setFirstDueDate(LocalDate firstDueDate) {
        this.firstDueDate = firstDueDate;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate addInterval(LocalDate firstDueDate, Interval interval, int quantity) {
        if (interval.equals(Interval.HOUR)) return firstDueDate.plus(quantity, ChronoUnit.HOURS);
        else if (interval.equals(Interval.DAY)) return firstDueDate.plusDays(quantity);
        else if (interval.equals(Interval.WEEK)) return getFirstDueDate().plusWeeks(quantity);
        else if (interval.equals(Interval.MONTH)) return getFirstDueDate().plusMonths(quantity);
        else if (interval.equals(Interval.YEAR)) return getFirstDueDate().plusYears(quantity);
        else return LocalDate.now();
    }
}
