package controller;

import app.AppContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudyController {

    private final AppContext context;

    @FXML
    private Label timerLabel;

    @FXML
    private Label todayHoursLabel;

    public StudyController(AppContext context) {
        this.context = context;
    }

    @FXML
    public void initialize() {
        // future logic
    }

    @FXML
    private void startSession() {
    }

    @FXML
    private void stopSession() {
    }
}