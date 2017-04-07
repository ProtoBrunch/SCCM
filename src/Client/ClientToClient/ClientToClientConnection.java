package Client.ClientToClient;

import Client.WebcamChatGui;
import com.github.sarxos.webcam.Webcam;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Veranwtortlich für den Verbindungsaufbau zu einem Client.
 * Versucht Verbindung zu einem Client aufzubauen, und, wenn erfolgreich, startet einen CTCTextListener-Thread und einen CTCTextWriter-Thread.
 *
 * @see CTCTextListener
 * @see CTCTextWriter
 * Created by Robin Berberat on 04.04.2017.
 */
public class ClientToClientConnection extends Thread {
    private String host;
    private Socket client = null;
    private Socket clientWebcam = null;
    private WebcamChatGui gui;
    private Webcam webcam = Webcam.getDefault();
    private boolean isItClientServer;
    private Scanner scanner = new Scanner(System.in);
    private String username;

    /**
     * Konstruktor
     *
     * @param adress
     */
    public ClientToClientConnection(String adress, boolean isItClientServer) {
        this.host = adress;
        this.isItClientServer = isItClientServer;
        webcam.setViewSize(new Dimension(640, 480));
    }

    /**
     * Verbindet den Client mit einem anderen Client über einen Socket,
     * <p/>
     * startet die nötigen CTC-Threads zur Kommunikation zwischen Clients, und stirbt daraufhin.
     */
    public void run() {
        try {
            System.out.println("What is your username?");
            username = scanner.nextLine();

            if (isItClientServer) {
                startServer();
            } else {
                connectToClient();
            }
            openGui();
            openListenersAndWriters();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Startet auf dem Client ein neuer Serversocket für den Chat und die Webcamübertragung
     * @throws IOException
     */
    private void startServer() throws IOException {
        ServerSocket clientServer = new ServerSocket(6000);
        ServerSocket clientWebcamServer = new ServerSocket(5000);

        client = clientServer.accept();
        clientWebcam = clientWebcamServer.accept();
    }

    /**
     * Startet auf dem Client ein neuer Socket für den Chat und die Webcamübertragung
     * @throws IOException
     */
    private void connectToClient() throws IOException {
        client = new Socket(host, 6000);
        clientWebcam = new Socket(host, 5000);
    }

    /**
     * Erstellt ein neues Webcamgui Objekt und öffnet dieses.
     */
    private void openGui() {
        gui = new WebcamChatGui(client, username);
        gui.setComponents();
    }

    /**
     * Erstellt für den Client einen WebcamWriter, WebcamListener und einen ChatListener
     * @throws IOException
     */
    private void openListenersAndWriters() throws IOException {
        new CTCTextListener(client, gui).start();
        new CTCWebcamWriter(clientWebcam, webcam, gui).start();
        new CTCWebcamListener(clientWebcam, gui).start();
    }

    /**
     * @return client
     */
    public Socket getClient() {
        return client;
    }

    /**
     * @return Ip adresse des Server
     */
    public String getHost() {
        return host;
    }
}

