package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 * Empfangspunkt für alle eingehenden Anweisungen des Servers.
 * Wird eine Anweisung empfangen, wird diese an einen ClientController weitergegeben.
 * <p/>
 * Erweitert die Thread-Klasse.
 * <p/>
 * Sollte die Verbindung zum Server geschlossen werden, stirbt der Thread.
 *
 * @see Thread
 * @see ClientController
 *
 * Created by Robin Berberat on 04.04.2017.
 */
public class ClientListener extends Thread{
    Socket server;
    BufferedReader inFromServer;

    /**
     * Konstruktor für den ClientListener.
     * Deklariert den BufferedReader für die Server-Inputs.
     *
     * @param server Socket-Verbindung zum Server.
     * @throws IOException
     */
    public ClientListener(Socket server) throws IOException {
        this.server = server;
        inFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    /**
     * Hört auf dem InputStream() bis eine Nachricht vom Server kommt, und startet einen ClientController sollten Nachrichten hineinkommen.
     *
     * Stirbt sollte der Socket-geschlossen werden.
     */
    public void run(){
        try{
            while(server.isConnected()) {
                String input = inFromServer.readLine();
                new ClientController(input, server);
            }


        } catch(SocketException se){
            try {
                inFromServer.close();
                System.out.println("Closed inputStream");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Closed Thread");
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
            this.interrupt();
        }

    }
}
