package Client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

/**
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamListener extends Thread{
    Socket client;
    private  WebcamChatGui gui;
    private  DataInputStream inFromClient;


    CTCWebcamListener(Socket client, WebcamChatGui gui) throws IOException{
        this.client = client;
        this.gui = gui;
        inFromClient = new DataInputStream(new BufferedInputStream(client.getInputStream()));
    }

    public void run(){
        try{
            while(client.isConnected()){
                int length = inFromClient.readInt();
                if(length >0 ) {
                    byte[] imageByteArray = new byte[length];
                    inFromClient.readFully(imageByteArray, 0, length);
                    BufferedImage imageback = ImageIO.read(new ByteArrayInputStream(imageByteArray));
                    gui.addNewImage(imageback, "extern");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
