import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private ServerController serverController;
    //  private PrintWriter out;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ClientHandler(Socket clientSocket, ServerController serverController) throws IOException {
        this.clientSocket = clientSocket;
        this.serverController = serverController;

        dataInputStream = new DataInputStream(clientSocket.getInputStream());
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            // out = new PrintWriter(clientSocket.getOutputStream(), true);
            //  BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                String message = dataInputStream.readUTF();
                serverController.warkingServer( message); //Received message from client: brodcast ekedi me message eka show karanawa
                serverController.broadcastMessage(message, this);  //anith client ta yawana eka
            }
        } catch (IOException e) {
            serverController.warkingServer("Error: " + e.getMessage());
        } finally {
            try {
                //   out.close();
                clientSocket.close();
                serverController.removeClient(this);
            } catch (IOException e) {
                serverController.warkingServer("Error closing client connection: " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        // out.println(message);
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
    }
}
