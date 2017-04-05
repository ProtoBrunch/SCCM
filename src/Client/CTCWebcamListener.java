package Client;

import java.io.*;
import java.net.Socket;

/**
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamListener extends Thread{
    Socket client;
    WebcamChatGui gui;
    DataInputStream inFromClient;


    public CTCWebcamListener(Socket client, WebcamChatGui gui) throws IOException{
        this.client = client;
        this.gui = gui;
        inFromClient = new DataInputStream(new BufferedInputStream(client.getInputStream()));
    }

    public void run(){
        try{
            while(client.isConnected()){
                byte[] imageByteArray = new byte[76032];
                inFromClient.readFully(imageByteArray, 0, 76032);
                System.out.println(imageByteArray);
                gui.addNewImage(imageByteArray, 640, 480, "extern");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
