package Controller;

import dto.ClientDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {



    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private TextField txtlogin;

    @FXML
    private Button btnlogin;


    @FXML
    void btnloginOnAction(ActionEvent event) throws IOException {
        if (txtlogin.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please Enter Name");
            alert.showAndWait();

        } else {

            ClientDTO clientDTO = new ClientDTO(txtlogin.getText());



            Stage stage = new Stage();
            Parent root = null;
            stage.setTitle(txtlogin.getText());
            stage.getIcons().add(new Image("assets/icons8-chat-96.png"));

            root = FXMLLoader.load(getClass().getResource("/view/client_form.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            adminAncPane.getScene().getWindow().hide();
        }
    }

    @FXML
    void trytytrytrtrytytrytr(KeyEvent event) throws IOException {
        Stage stage = new Stage();

        Parent root = null;
        stage.setTitle(txtlogin.getText());

        root = FXMLLoader.load(getClass().getResource("/view/client_form.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        adminAncPane.getScene().getWindow().hide();
    }

}
