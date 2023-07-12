package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        URL resource = Main.class.getResource("sample.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));

        stage.setTitle("SPICY FLAVOUR");
        stage.centerOnScreen();
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
