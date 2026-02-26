package ui;

import app.AppContext;
import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ViewLoader {

    private final AppContext context;

    public ViewLoader(AppContext context) {
        this.context = context;
    }

    public Parent loadMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/main.fxml")
            );

            loader.setControllerFactory(param -> {
                if (param == MainController.class) {
                    return new MainController(context);
                }
                try {
                    return param.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            return loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}