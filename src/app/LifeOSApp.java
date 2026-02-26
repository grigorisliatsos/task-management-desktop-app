package app;

import database.DatabaseManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.TaskRepository;
import repository.sqlite.SqliteTaskRepository;
import service.TaskService;
import ui.ViewLoader;

public class LifeOSApp extends Application {

    private AppContext context;

    @Override
    public void start(Stage stage) {

        DatabaseManager dbManager = new DatabaseManager();
        TaskRepository taskRepository = new SqliteTaskRepository(dbManager);
        TaskService taskService = new TaskService(taskRepository);

        context = new AppContext(taskService);

        ViewLoader loader = new ViewLoader(context);
        Scene scene = new Scene(loader.loadMainView());

        stage.setTitle("LifeOS");
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}