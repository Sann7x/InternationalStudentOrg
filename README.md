# InternationalStudentOrg
International Student Organizer App

The International Student Organizer is a Java application designed to help international students manage tasks, visa deadlines, and work schedules. Built with Java and JavaFX, it provides a simple interface for tracking personal info, adding tasks, and getting reminders for due or overdue items.

Features

Student Information: Store name, student ID, country, visa expiry date, and weekly work limits.

Task Management: Add tasks in Academic, Visa, or Work categories.

Reminders: Get alerts for tasks that are due or overdue.

Work Hour Monitoring: Keep track of weekly work hours with warnings if limits are exceeded.

User-Friendly GUI: Tabbed interface for student info, adding tasks, and viewing tasks.

System Architecture

Student Class: Stores student details and validates work hours.

Task Class (Abstract): Base class for all tasks with title, due date, and completion status.

TaskManager Class: Manages all tasks and provides retrieval methods.

JavaFX GUI: Tabbed layout for easy navigation and visual notifications.

Screenshots

Main interface with tabs for student info, adding tasks, and viewing tasks.

Task table highlighting due/overdue tasks with checkboxes to mark completion.

Future Enhancements

Persistent storage with a database.

Automated notifications for visa expiry and upcoming deadlines.

Sorting and filtering tasks by category or due date.

Improved GUI with interactive elements and color-coded task types.

How to Run

Clone or download the repository.

Open the project in IntelliJ IDEA.

Ensure JavaFX is set up in your project (module paths and VM options).

Run Main.java to launch the application.

