import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Client2Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL resource = Client2Main.class.getResource("view/login_form.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));

        stage.setTitle("login page");
        stage.centerOnScreen();
        stage.show();
    }
}
