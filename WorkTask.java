import java.time.LocalDate;

public class WorkTask extends Task {

    public WorkTask(String title, LocalDate dueDate) {
        super(title, dueDate);
    }

    @Override
    public String getCategory() {
        return "Work";
    }

    @Override
    public void display() {
        System.out.print("[Work] " + title + " | Due: " + dueDate +
                " | Completed: " + completed);

        if (isDueOrOverdue() && !completed) {
            System.out.print("  >>> REMINDER: DUE OR OVERDUE!");
        }

        System.out.println();
    }

}
