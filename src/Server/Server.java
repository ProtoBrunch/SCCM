package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by User on 04.04.2017.
 */
public class Server {

    public static void main(String[] args) {
        int port = 50000;

        try(ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server gestartet");
            while(true){
                Socket client = server.accept();
                System.out.println("Client verbunden");
                new ServerListener(client).start();
                System.out.println("Listener gestartet");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
