import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerController {

    @FXML
    private VBox txtVBox;

    @FXML
    private Button btnSend;

    @FXML
    private TextField txtFeald;

    @FXML
    private TextArea txtArea;

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        /*String typeText = txtFeald.getText();
        dataOutputStream.writeUTF(typeText);
        dataOutputStream.flush();
        txtFeald.setText("");*/
    }

    private List<ClientHandler> clients = new ArrayList<>();

    @FXML
    void initialize() throws IOException {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8888); //server socket
                warkingServer("Server started. Listening for clients...");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    warkingServer("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                    // Create a new client handler thread
                    ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                    clients.add(clientHandler);
                    clientHandler.start();
                }
            } catch (IOException e) {
                warkingServer("Error: " + e.getMessage());
            }
        }).start();
    }

    public void warkingServer(String message) {
        if (message.startsWith("C:")) {
            Image image = new Image("file:" + message);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);

            Platform.runLater(()->{
                txtVBox.getChildren().add(new Label("Received message from client : "));
                txtVBox.getChildren().add(imageView);
            });
        } else {
            Platform.runLater(()->{
                Label label = new Label(message);
                txtVBox.getChildren().add(label);
            });
        }
    }

    public void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
