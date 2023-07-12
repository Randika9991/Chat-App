import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class ClientMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("assets/icons8-chat-96.png"));
        URL resource = ClientMain.class.getResource("view/login_form.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));

        stage.setTitle("login page");
        stage.centerOnScreen();
        stage.show();
    }
}
