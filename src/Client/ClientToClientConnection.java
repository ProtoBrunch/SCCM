package Client;

import com.github.sarxos.webcam.Webcam;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
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
    private  String host;
    private  int port;
    Socket client = null;
    private  Socket clientWebcam = null;
    private  WebcamChatGui gui;
    private  Webcam webcam = Webcam.getDefault();
    private String type;

    /**
     * Konstruktor
     *
     * @param adress
     * @param port
     */
    public ClientToClientConnection(String adress, int port) {
        this.host = adress;
        this.port = port;
        webcam.setViewSize(new Dimension(640,480));
    }

    public ClientToClientConnection()

    /**
     * Verbindet den Client mit einem anderen Client über einen Socket,
     * <p/>
     * startet die nötigen CTC-Threads zur Kommunikation zwischen Clients, und stirbt daraufhin.
     */
    public void run() {


    }

    private void openGui(){
        gui = new WebcamChatGui(client);
        gui.setComponents();
    }

    private void openListenersAndWriters() throws IOException {
        new CTCListener(client, gui).start();
        new CTCWebcamWriter(clientWebcam, webcam, gui).start();
        new CTCWebcamListener(clientWebcam, gui).start();
    }

    private void startServer() throws IOException {
        ServerSocket clientServer = new ServerSocket(port);
        ServerSocket clientWebcamServer = new ServerSocket(5000)

        client = clientServer.accept();
        Socket webcamClient = clientWebcamServer.accept();
    }

    private void connectToClient() throws IOException {
        client = new Socket(host, port); // Establish Connections for Text-
        clientWebcam = new Socket(host,5000); // and Webcam-Data Transfer
    }
}
