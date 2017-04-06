package Client;

import Client.ClientToServer.CTSListener;

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
    /**
     * MainMethode der des Programm
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 50000;

        Socket server = new Socket(host, port);
        new CTSListener(server).start();
    }
}
