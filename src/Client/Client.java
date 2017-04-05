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
    public static void main(String[] args) throws IOException {
        initiateClient("localhoat", 50000);
    }

    private static void initiateClient(String host, int port){
        try ( Socket server = new Socket(host, port);){
            new CTSListener(server).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
