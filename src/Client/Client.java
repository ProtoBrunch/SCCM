package Client;

import java.io.IOException;
import java.net.Socket;

/**
 * Startpunkt des Verbindungsaufbaues für Clients.
 * Erstellt eine Verbindung zum Server und startet auf der Verbindung einen CTSListener-Thread.
 * Danach stirbt der main()-Thread.
 *
 * @see CTSListener
 *
 * Created by Robin Berberat on 04.04.2017.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String host = "172.16.2.156";
        int port = 50000;
        Socket server;

        try {
            server = new Socket(host, port);
            new CTSListener(server).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
