package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Erstellt einen Serversocket, welcher kontinuierlich offen steht für Clients, die sich verbinden wollen.
 * <p>
 * Bei erfolgreicher Verbindung mit einem Client, wird dessen Serversocket and einen ServerListener-Thread übergeben.
 *
 * Created by Robin Berberat on 04.04.2017.
 */
public class Server {

    /**
     * MainMethode des Server vom Programm. Erstellt einen neuen Serversocket und Serverlistener auf den hinzugefügten client.
     * @param args
     */
    public static void main(String[] args) {
        int port = 50000;

        try(ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server gestartet");
            while(true){
                Socket client = server.accept();
                new ServerListener(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
