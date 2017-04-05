package Client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamListener extends Thread{
    Socket client;
    WebcamChatGui gui;
    InputStream inFromClient;
    ByteArrayOutputStream byteArrayGetter = new ByteArrayOutputStream();


    public CTCWebcamListener(Socket client, WebcamChatGui gui) throws IOException{
        this.client = client;
        this.gui = gui;
        inFromClient = client.getInputStream();
    }

    public void run(){
        while(client.isConnected()){
            byte[] imageByteArray = new byte[76032];
            int bytesRead = -1;
            try {
                while((bytesRead = inFromClient.read(imageByteArray))!= -1){
                    byteArrayGetter.write(imageByteArray,0,bytesRead);
                }
                gui.addNewImage(imageByteArray, 640, 480, "extern");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
