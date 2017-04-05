package Client;

import com.github.sarxos.webcam.Webcam;

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
    Socket clientWebcam = null;
    WebcamChatGui gui;
    Webcam webcam = Webcam.getDefault();

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

        // Establish Connections for Text- and Webcam-Data Transfer
        try {
            client = new Socket(host, port);
            System.out.println("verbunden zum Textzeug");
            clientWebcam = new Socket(host,5000);
            System.out.println("verbunden zum Webcamzeug");

            gui = new WebcamChatGui(client);
            gui.setComponents();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add Listeners and Writers
        try {
            new CTCListener(client, gui).start();
            System.out.println("listener start");
            new CTCWebcamListener(client, gui).start();
            new CTCWebcamWriter(client, webcam).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
