import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class StudentOrganizerGUI extends Application {

    private TaskManager taskManager = new TaskManager();
    private Student student;

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();


        Tab studentInfoTab = new Tab("Student Info");
        VBox studentLayout = new VBox(10);
        studentLayout.setPadding(new javafx.geometry.Insets(10));
        studentLayout.setStyle("-fx-background-color: #fae8e8;");

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");

        TextField idField = new TextField();
        idField.setPromptText("Student ID");

        TextField countryField = new TextField();
        countryField.setPromptText("Country");

        DatePicker visaDatePicker = new DatePicker();
        visaDatePicker.setPromptText("Visa Expiry Date");

        TextField maxHoursField = new TextField();
        maxHoursField.setPromptText("Max Weekly Hours");

        Button saveButton = new Button("Save Student Info");

        Label statusLabel1 = new Label("");

        TextField currentHoursField = new TextField();
        currentHoursField.setPromptText("Hours worked this week");

        Button checkHoursButton = new Button("Check Hours");

        Label hoursWarningLabel = new Label("");

        studentLayout.getChildren().addAll(nameField, idField, countryField, visaDatePicker,
                maxHoursField, saveButton, statusLabel1,
                currentHoursField, checkHoursButton, hoursWarningLabel);

        studentInfoTab.setContent(studentLayout);

        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String country = countryField.getText();
            LocalDate visaDate = visaDatePicker.getValue();
            int maxHours;

            try {
                maxHours = Integer.parseInt(maxHoursField.getText());
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max Weekly Hours must be a number!");
                alert.show();
                return;
            }

            student = new Student(name, id, country, visaDate, maxHours);
            statusLabel1.setText("Student information saved!");
        });

        checkHoursButton.setOnAction(e -> {
            if (student == null) {
                hoursWarningLabel.setText("Enter student info first!");
                return;
            }
            int hours;
            try {
                hours = Integer.parseInt(currentHoursField.getText());
            } catch (NumberFormatException ex) {
                hoursWarningLabel.setText("Enter a valid number!");
                return;
            }
            if (hours > student.getMaxWorkHours()) {
                hoursWarningLabel.setText("Warning: Exceeds max weekly hours!");
            } else {
                hoursWarningLabel.setText("Hours are within limit.");
            }
        });


        Tab addTaskTab = new Tab("Add Task");
        VBox addTaskLayout = new VBox(10);
        addTaskLayout.setPadding(new javafx.geometry.Insets(10));
        addTaskLayout.setStyle("-fx-background-color: #e8f4fa;");

        TextField titleField = new TextField();
        titleField.setPromptText("Task Title");

        DatePicker dueDatePicker = new DatePicker();

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Academic", "Visa", "Work");
        categoryBox.setValue("Academic");

        Button addButton = new Button("Add Task");

        Label taskCountLabel = new Label("Total tasks: 0");

        addTaskLayout.getChildren().addAll(titleField, dueDatePicker, categoryBox, addButton, taskCountLabel);
        addTaskTab.setContent(addTaskLayout);


        Tab viewTaskTab = new Tab("View Tasks");
        VBox viewTaskLayout = new VBox(10);
        viewTaskLayout.setPadding(new javafx.geometry.Insets(10));
        viewTaskLayout.setStyle("-fx-background-color: #f0e8fa;");

        TableView<Task> taskTable = new TableView<>();

        TableColumn<Task, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().title));

        TableColumn<Task, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));

        TableColumn<Task, String> dueCol = new TableColumn<>("Due Date");
        dueCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().dueDate.toString()));

        TableColumn<Task, Boolean> completedCol = new TableColumn<>("Completed");
        completedCol.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isCompleted()));
        completedCol.setCellFactory(CheckBoxTableCell.forTableColumn(completedCol));
        completedCol.setEditable(true);

        TableColumn<Task, String> reminderCol = new TableColumn<>("Reminder");
        reminderCol.setCellValueFactory(data -> {
            if (data.getValue().isDueOrOverdue() && !data.getValue().isCompleted()) {
                return new javafx.beans.property.SimpleStringProperty("DUE OR OVERDUE!");
            } else {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        taskTable.getColumns().addAll(titleCol, categoryCol, dueCol, completedCol, reminderCol);
        taskTable.setEditable(true);

        viewTaskLayout.getChildren().add(taskTable);
        viewTaskTab.setContent(viewTaskLayout);

        tabPane.getTabs().addAll(studentInfoTab, addTaskTab, viewTaskTab);


        addButton.setOnAction(e -> {
            if (student == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter student info first!");
                alert.show();
                return;
            }

            String title = titleField.getText();
            LocalDate dueDate = dueDatePicker.getValue();
            String category = categoryBox.getValue();

            if (title.isEmpty() || dueDate == null) return;

            switch (category) {
                case "Academic" -> taskManager.addTask(new AcademicTask(title, dueDate));
                case "Visa" -> taskManager.addTask(new VisaTask(title, dueDate));
                case "Work" -> taskManager.addTask(new WorkTask(title, dueDate));
            }

            titleField.clear();
            dueDatePicker.setValue(null);

            taskTable.getItems().setAll(taskManager.getTasks());
            taskCountLabel.setText("Total tasks: " + taskManager.getTasks().size());
        });

        Scene scene = new Scene(tabPane, 700, 450);
        stage.setScene(scene);
        stage.setTitle("International Student Organizer");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}