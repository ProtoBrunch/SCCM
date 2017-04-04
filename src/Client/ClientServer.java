package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Startet einen Clientseitigen ServerSocket, auf den sich ein anderer Client der zu chatten wünscht verbinden kann.
 * Wartet nach Start darauf, dass sich ein Client verbindet.
 * <p/>
 * Verbindet sich ein Client, werden zwei Threads, CTCListener und CTCWriter gestartet, und dieser Thread stirbt.
 *
 * @see Thread
 * @see ServerSocket
 * @see CTCListener
 * @see CTCWriter
 *
 * Created by Robin Berberat on 04.04.2017.
 */
public class ClientServer extends Thread{
    int port;
    Socket client = null;

    /**
     * Konstruktor für ClientServer
     * @param port
     */
    public ClientServer(String port) {
        this.port = Integer.parseInt(port);
    }

    /**
     * Öffnet ServerSocket nach aussen und wartet auf einen Verbinder.
     * Nach Verbindungsaufbau werden ein Listener und ein Writer gestartet, und der Thread stirbt.
     */
    public void run(){
        try(ServerSocket clientServer = new ServerSocket(port)) {
            System.out.println("Server gestartet und wartet auf Client.");
            client = clientServer.accept();

            new CTCListener(client).start();
            new CTCWriter(client).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
