import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void showTasks(Student student) {
        System.out.println("\n--- Your Tasks ---");

        int index = 1;


        if (student.isVisaExpiringSoon()) {
            for (Task task : tasks) {
                if (task.getCategory().equals("Visa") && !task.isCompleted()) {
                    System.out.print(index + ". ");
                    task.display();
                    index++;
                }
            }
        }


        for (Task task : tasks) {
            if (!task.isCompleted() && !task.getCategory().equals("Visa")) {
                System.out.print(index + ". ");
                task.display();
                index++;
            }
        }


        for (Task task : tasks) {
            if (task.isCompleted()) {
                System.out.print(index + ". ");
                task.display();
                index++;
            }
        }
    }

    public void markTaskCompleted(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
