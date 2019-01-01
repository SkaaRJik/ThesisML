package ru.filippov.ml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.filippov.ml.sample.Controller;

public class Main extends Application {
    FXMLLoader fxmlLoader;
    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("views/sample.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        Controller controller = fxmlLoader.getController();
        controller.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
