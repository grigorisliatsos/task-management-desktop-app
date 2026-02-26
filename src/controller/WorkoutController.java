package controller;

import app.AppContext;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

public class WorkoutController {

    private final AppContext context;

    @FXML
    private TextField exerciseField;

    @FXML
    private TextField durationField;

    @FXML
    private ListView<String> workoutList;

    public WorkoutController(AppContext context) {
        this.context = context;
    }

    @FXML
    public void initialize() {
        // future workout logic
    }

    @FXML
    private void addWorkout() {
    }
}