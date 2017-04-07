package Client.ClientToClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client-To-Client Writer
 * Instanziert einen PrintWriter auf dem Outputstream, sowie einen Scanner, damit ein Client Nachrichten in String-form verschicken kann.
 *
 * Created by Robin Berberat 04.04.2017.
 */
public class CTCTextWriter extends Thread{
    private PrintWriter outToClient;
    private String string;

    /**
     * Konstruktor
     *
     * @param client Client, welcher Nachricht veschickt.
     * @throws IOException
     */
    public CTCTextWriter(Socket client, String input) throws IOException {
        outToClient = new PrintWriter(client.getOutputStream(),true);
        this.string = input;
    }

    /**
     * Solange die Verbindung besteht, wartet der Thread auf eine Kommandozeileneingabe.
     * Bei eingabe eines Textes, wird dieser an den anderen Client gesendet.
     *<p>
     * Sobald die Verbindung zusammenbricht, stribt der Thread.
     */
    public void run(){
            outToClient.println(string);
    }
}
