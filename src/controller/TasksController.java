package controller;

import app.AppContext;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Task;
import service.TaskService;

public class TasksController {

    private final TaskService taskService;

    @FXML
    private TextField taskInput;

    @FXML
    private ListView<Task> taskList;

    public TasksController(AppContext context) {
        this.taskService = context.getTaskService();
    }

    @FXML
    public void initialize() {
        refreshTasks();

        taskList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);

                if (empty || task == null) {
                    setText(null);
                } else {
                    CheckBox checkBox = new CheckBox(task.getTitle());
                    checkBox.setSelected(task.isCompleted());

                    checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                        task.setCompleted(newVal);
                        taskService.toggleTask(task);
                    });

                    setGraphic(checkBox);
                }
            }
        });
    }

    @FXML
    private void addTask() {
        taskService.addTask(taskInput.getText());
        taskInput.clear();
        refreshTasks();
    }

    private void refreshTasks() {
        taskList.setItems(
                FXCollections.observableArrayList(
                        taskService.getAllTasks()
                )
        );
    }
    @FXML
    private void deleteTask() {
        Task selected = taskList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            taskService.deleteTask(selected);
            refreshTasks();
        }
    }
}