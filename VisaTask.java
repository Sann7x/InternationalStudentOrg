import java.time.LocalDate;

public class VisaTask extends Task {

    public VisaTask(String title, LocalDate dueDate) {
        super(title, dueDate);
    }

    @Override
    public String getCategory() {
        return "Visa";
    }

    @Override
    public void display() {
        System.out.print("[Visa] " + title + " | Due: " + dueDate +
                " | Completed: " + completed);

        if (isDueOrOverdue() && !completed) {
            System.out.print("  >>> REMINDER: DUE OR OVERDUE!");
        }

        System.out.println();
    }

}
