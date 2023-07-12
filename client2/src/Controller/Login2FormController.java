package Controller;

import dto.Client2DTO;
import dto.Client2DTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Login2FormController {

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

            Client2DTO clientDTO = new Client2DTO(txtlogin.getText());



            Stage stage = new Stage();
            Parent root = null;
            stage.setTitle(txtlogin.getText());

            root = FXMLLoader.load(getClass().getResource("/view/client2_form.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            adminAncPane.getScene().getWindow().hide();
        }


    }

}
