package Client.ClientToClient;

import Client.WebcamChatGui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Client-To-Client Listener
 * Instanziert einen Bufferedreader auf dem Inputstream um Nachrichten des anderen Clients empfangen zu können
 *
 * Created by Robin Berberat 04.04.2017.
 */
public class CTCTextListener extends Thread{
    private Socket client;
    private BufferedReader inFromClient;
    private WebcamChatGui gui;

    /**
     * Konstruktor
     *
     * @param client Client, welcher Nachrichten erhält.
     * @throws IOException
     */
    CTCTextListener(Socket client, Client.WebcamChatGui gui) throws IOException {
        this.client = client;
        inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.gui = gui;
    }

    /**
     * Solange die Verbindung besteht, wartet der Thread auf ein einkommendes Signal vom anderen Client.
     * <p>
     * Sobald die Verbindung zusammenbricht, stribt der Thread.
     */
    public void run(){
        try {
            while(client.isConnected()){
                String input = inFromClient.readLine();
                gui.addNewMessage(input);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
