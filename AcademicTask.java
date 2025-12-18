import java.time.LocalDate;

public class AcademicTask extends Task {

    public AcademicTask(String title, LocalDate dueDate) {
        super(title, dueDate);
    }

    @Override
    public String getCategory() {
        return "Academic";
    }

    @Override
    public void display() {
        System.out.print("[Academic] " + title + " | Due: " + dueDate +
                " | Completed: " + completed);

        if (isDueOrOverdue() && !completed) {
            System.out.print("  >>> REMINDER: DUE OR OVERDUE!");
        }

        System.out.println();
    }
}
