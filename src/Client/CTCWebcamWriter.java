package Client;

import com.github.sarxos.webcam.Webcam;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamWriter extends Thread{
    Socket client;
    Webcam webcam;
    DataOutputStream outToClient;



    public CTCWebcamWriter(Socket client, Webcam webcam) throws IOException {
        this.client = client;
        this.webcam = webcam;
        if(!webcam.isOpen()){
            webcam.open();
        }
        outToClient = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
    }

    public void run(){
        while (client.isConnected()){
            ByteBuffer byteBuffer = webcam.getImageBytes();
            byte[] data = new byte[byteBuffer.capacity()];
            ((ByteBuffer) byteBuffer.duplicate().clear()).get(data);

            try {
                outToClient.write(data);
                outToClient.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
