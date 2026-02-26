package controller;

import app.AppContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    private final AppContext context;

    @FXML
    private BorderPane mainBorderPane;

    public MainController(AppContext context) {
        this.context = context;
    }

    @FXML
    public void initialize() {
        loadDashboard();
    }

    /* =========================
       PUBLIC VIEW LOADERS
       ========================= */

    @FXML
    private void loadDashboard() {
        loadView("/view/dashboard.fxml", DashboardController.class);
    }

    @FXML
    private void loadTasks() {
        loadView("/view/tasks.fxml", TasksController.class);
    }

    @FXML
    private void loadStudy() {
        loadView("/view/study.fxml", StudyController.class);
    }

    @FXML
    private void loadWorkout() {
        loadView("/view/workout.fxml", WorkoutController.class);
    }

    /* =========================
       GENERIC VIEW LOADER
       ========================= */

    private <T> void loadView(String fxmlPath, Class<T> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlPath)
            );

            loader.setControllerFactory(param -> {

                // Αν είναι ο controller που ζητάμε → constructor injection
                if (param == controllerClass) {
                    try {
                        return controllerClass
                                .getConstructor(AppContext.class)
                                .newInstance(context);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                // Default constructor για άλλα nodes/controllers
                try {
                    return param.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            mainBorderPane.setCenter(loader.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}