package Client;

import java.io.IOException;
import java.net.Socket;

/**
 * Veranwtortlich für den Verbindungsaufbau zu einem Client.
 * Versucht Verbindung zu einem Client aufzubauen, und, wenn erfolgreich, startet einen CTCListener-Thread und einen CTCWriter-Thread.
 *
 * @see CTCListener
 * @see CTCWriter
 * Created by Robin Berberat on 04.04.2017.
 */
public class ClientToClientConnection extends Thread{
    String host;
    int port;
    Socket client = null;

    /**
     * Konstruktor
     *
     * @param adress
     * @param port
     */
    public ClientToClientConnection(String adress, int port) {
        this.host = adress;
        this.port = port;
    }

    /**
     * Verbindet den Client mit einem anderen Client über einen Socket,
     * <p/>
     * startet die nötigen CTC-Threads zur Kommunikation zwischen Clients, und stirbt daraufhin.
     */
    public void run() {
        try {
            client = new Socket(host, port);
            System.out.println("verbunden");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            new CTCListener(client).start();
            System.out.println("listener start");
            new CTCWriter(client).start();
            System.out.println("writer start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
