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
        initiateClient("172.16.2.137", 50000); //Host = Ipadresse des Server
    }

    /**
     * Neuer Socket zum Server wird erstellt. Anschliessend ein ListenerThread gestartet.
     * @param host IP-Adresse vom Server
     * @param port Port des Server.
     */
    private static void initiateClient(String host, int port) throws IOException{
            Socket server = new Socket(host, port);
            new CTSListener(server).start();

    }
}
