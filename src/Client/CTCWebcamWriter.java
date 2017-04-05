package Client;

import com.github.sarxos.webcam.Webcam;

import java.net.Socket;

/**
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamWriter extends Thread{
    Socket client;
    Webcam webcam;



    public CTCWebcamWriter(Socket client, Webcam webcam) {

    }
}
