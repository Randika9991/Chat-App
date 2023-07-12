package Controller;

import dto.Client2DTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client2FormController {

    @FXML
    private AnchorPane ImoAncherPain;

    /*@FXML
    private ImageView ImoImage;
*/
    @FXML
    private ScrollPane imoScrolle;

    @FXML
    private VBox imoVBox;

    public AnchorPane adminAncPane;

    public VBox txtVBox;

    @FXML
    private ImageView openButton;

    @FXML
    private TextField txtFeald;

    @FXML
    private Button imoBtn;

    Label emojiLabel;

    private void displayFoodEmojis() {
        imoVBox.getChildren().clear();
        // Create a button for each emoji
        String[] emojis = {
                "\uD83C\uDF30", "\uD83C\uDF31", "\uD83C\uDF32", "\uD83C\uDF33", "\uD83C\uDF34",
                "\uD83C\uDF35", "\uD83C\uDF36", "\uD83C\uDF37", "\uD83C\uDF38", "\uD83C\uDF39",
                "\uD83C\uDF3A", "\uD83C\uDF3B", "\uD83C\uDF3C", "\uD83C\uDF3D", "\uD83C\uDF3E",
                "\uD83C\uDF3F", "\uD83C\uDF40", "\uD83C\uDF41", "\uD83C\uDF42", "\uD83C\uDF43",
                "\uD83C\uDF44", "\uD83C\uDF45", "\uD83C\uDF46", "\uD83C\uDF47", "\uD83C\uDF48",
                "\uD83C\uDF49", "\uD83C\uDF4A", "\uD83C\uDF4B", "\uD83C\uDF4C", "\uD83C\uDF4D",
                "\uD83C\uDF4E", "\uD83C\uDF4F", "\uD83C\uDF50", "\uD83C\uDF51", "\uD83C\uDF52",
                "\uD83C\uDF53", "\uD83C\uDF54", "\uD83C\uDF55", "\uD83C\uDF56", "\uD83C\uDF57",
                "\uD83C\uDF58", "\uD83C\uDF59", "\uD83C\uDF5A", "\uD83C\uDF5B", "\uD83C\uDF5C",
                "\uD83C\uDF5D", "\uD83C\uDF5E", "\uD83C\uDF5F", "\uD83C\uDF60", "\uD83C\uDF61",
                "\uD83C\uDF62", "\uD83C\uDF63", "\uD83C\uDF64", "\uD83C\uDF65", "\uD83C\uDF66",
                "\uD83C\uDF67", "\uD83C\uDF68", "\uD83C\uDF69", "\uD83C\uDF6A", "\uD83C\uDF6B",
                "\uD83C\uDF6C", "\uD83C\uDF6D", "\uD83C\uDF6E", "\uD83C\uDF6F", "\uD83C\uDF70",
                "\uD83C\uDF71", "\uD83C\uDF72", "\uD83C\uDF73", "\uD83C\uDF74", "\uD83C\uDF75",
                "\uD83C\uDF76", "\uD83C\uDF77", "\uD83C\uDF78", "\uD83C\uDF79", "\uD83C\uDF7A",
                "\uD83C\uDF7B", "\uD83C\uDF7C", "\uD83C\uDF7D", "\uD83C\uDF7E", "\uD83C\uDF7F"

        };

        for (String emoji : emojis) {
            Label emojiLabel = new Label();
            emojiLabel.setText(emoji);
            emojiLabel.setStyle("-fx-font-size: 30");
            emojiLabel.setOnMouseClicked(event -> {
                String unicode = emoji;
                // sendTxtAreaClient.appendText(emoji);
                // sendTxtAreaClient.requestFocus();
                //  sendTxtAreaClient.positionCaret(sendTxtAreaClient.getText().length());
            });
            imoVBox.getChildren().add(emojiLabel);
        }
    }

    @FXML
    void imoButtonOnAction(ActionEvent event) {

        imoVBox.setVisible(true);
        imoScrolle.setVisible(true);

        Platform.runLater(()->{
            displayFoodEmojis();
            clickImogy();
        });
    }

    private void clickImogy() {
        emojiLabel.setOnMouseClicked(event -> {
            System.out.println(emojiLabel);
            txtFeald.setText(emojiLabel.getText());
        });
    }

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        if (txtFeald.getText().isEmpty()) {

        } else {
            String message = txtFeald.getText();
            dataOutputStream.writeUTF(Client2DTO.getName() +" : " +message);
            dataOutputStream.flush();
            addToVBox("you" +" : " + message);
            txtFeald.clear();
        }
    }

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    private Socket socket;
    @FXML
    void initialize() throws IOException {
        //ImoImage.setVisible(false);
        imoVBox.setVisible(false);
        imoScrolle.setVisible(false);
        new Thread(()->{
            try {
                socket = new Socket("localhost", 8888);
                //socket = new Socket("192.168.180.199", 5003);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                Thread readerThread = new Thread(() -> {
                    try {
                        while (true) {
                            String message = dataInputStream.readUTF();
                            addToVBox(message);
                        }
                    } catch (IOException e) {
                        addToVBox("Error reading from server: " + e.getMessage());
                    }
                });
                readerThread.start();
            } catch (IOException e) {
                addToVBox("Error connecting to server: " + e.getMessage());
            }
        }).start();
    }

    public void addToVBox(String message) {
        if (message.startsWith("C:")) {
            hadleImageLink(message);
        } else {
            Platform.runLater(()->{
                Label label = new Label(message);
                txtVBox.getChildren().add(label);
                //  imoVBox.getChildren().add(label);
            });
        }
    }

    public void fileOnAction(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif")
        );
        Stage stage = (Stage) openButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String path = selectedFile.getAbsolutePath();
            System.out.println(path);
            // dataOutputStream.writeUTF("image");
            //  dataOutputStream.writeUTF(ClientDTO.getName());
            System.out.println(Client2DTO.getName());
            dataOutputStream.writeUTF(path);
            dataOutputStream.flush();

            hadleImageLink(path);
        }
    }

    private void hadleImageLink(String path) {
        Image image = new Image("file:" + path);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        Platform.runLater(()->{
            txtVBox.getChildren().add(new Label("Ýou : "));
            txtVBox.getChildren().add(imageView);
        });
    }
}


