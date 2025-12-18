import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        System.out.println("International Student Daily Organizer");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter country: ");
        String country = scanner.nextLine();

        System.out.print("Enter visa expiry date (YYYY-MM-DD): ");
        LocalDate visaDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter max weekly work hours: ");
        int maxHours = scanner.nextInt();

        Student student = new Student(name, id, country, visaDate, maxHours);

        int choice;

        do {
            System.out.println("\n1. Add Academic Task");
            System.out.println("2. Add Visa Task");
            System.out.println("3. Add Work Task");
            System.out.println("4. View Tasks");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Enter current weekly work hours:");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= 3) {
                System.out.print("Task title: ");
                String title = scanner.nextLine();

                System.out.print("Due date (YYYY-MM-DD): ");
                LocalDate dueDate = LocalDate.parse(scanner.nextLine().trim());


                if (choice == 1)
                    taskManager.addTask(new AcademicTask(title, dueDate));
                else if (choice == 2)
                    taskManager.addTask(new VisaTask(title, dueDate));
                else
                    taskManager.addTask(new WorkTask(title, dueDate));
            }

            if (choice == 4) {
                taskManager.showTasks(student);
            }

            if (choice == 5) {
                System.out.print("Enter task number to mark completed: ");
                int taskNum = scanner.nextInt();
                taskManager.markTaskCompleted(taskNum);
            }

            if (choice == 6) {
                System.out.print("Enter hours worked this week: ");
                int hours = scanner.nextInt();
                student.checkWorkHours(hours);
            }


        } while (choice != 0);

        System.out.println("Goodbye!");
        scanner.close();
    }
}
