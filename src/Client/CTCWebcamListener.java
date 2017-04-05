package Client;

import java.io.InputStream;
import java.net.Socket;

/**
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamListener extends Thread{
    Socket client;
    WebcamChatGui gui;
    InputStream inFromClient;


    public CTCWebcamListener(Socket client, WebcamChatGui gui) {
        this.client = client;
        try()
    }
}
