package Client.ClientToServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 * Client-To-Server Listener
 * Empfangspunkt für alle eingehenden Anweisungen des Servers.
 * Wird eine Anweisung empfangen, wird diese an einen CTSController weitergegeben.
 * <p/>
 * Erweitert die Thread-Klasse.
 * <p/>
 * Sollte die Verbindung zum Server geschlossen werden, stirbt der Thread.
 *
 * @see Thread
 * @see CTSController
 *
 * Created by Robin Berberat on 04.04.2017.
 */
public class CTSListener extends Thread{
    private  Socket server;
    private  BufferedReader inFromServer;

    /**
     * Konstruktor für den CTSListener.
     * Deklariert den BufferedReader für die Server-Inputs.
     *
     * @param server Socket-Verbindung zum Server.
     * @throws IOException
     */
    public CTSListener(Socket server) throws IOException {
        this.server = server;
        inFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    /**
     * Hört auf dem InputStream() bis eine Nachricht vom Server kommt, und startet einen CTSController sollten Nachrichten hineinkommen.
     *
     * Stirbt sollte der Socket-geschlossen werden.
     */
    public void run(){
        try{
            while(server.isConnected()) {
                String input = inFromServer.readLine();
                new CTSController(input, server);
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
