import java.time.LocalDate;

public abstract class Task {

    protected String title;
    protected LocalDate dueDate;
    protected boolean completed;

    public Task(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public void markCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isDueOrOverdue() {
        LocalDate today = java.time.LocalDate.now();
        if (completed) return false;        // ignore completed tasks
        if (dueDate == null) return false;  // safety check
        return !dueDate.isAfter(today);     // true if due date <= today
    }




    public abstract String getCategory();

    public abstract void display();
}
