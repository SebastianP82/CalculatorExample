package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private  Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Calculator");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
