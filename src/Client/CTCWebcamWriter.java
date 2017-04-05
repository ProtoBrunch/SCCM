package Client;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamWriter extends Thread{
    Socket client;
    private Webcam webcam;
    private  DataOutputStream outToClient;
    private  WebcamChatGui gui;

    public CTCWebcamWriter(Socket client, Webcam webcam, WebcamChatGui gui) throws IOException {
        this.client = client;
        this.webcam = webcam;
        this.gui = gui;
        if(!webcam.isOpen()){
            webcam.open();
        }
        outToClient = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
    }

    public void run(){
        while (client.isConnected()){
            while(webcam.isOpen()) {
                try {
                    BufferedImage webcamImage = webcam.getImage();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(webcamImage, "png", baos);
                    baos.flush();
                    byte[] data = baos.toByteArray();
                    baos.close();
                    outToClient.writeInt(data.length);
                    outToClient.write(data);
                    BufferedImage imageback = ImageIO.read(new ByteArrayInputStream(data));
                    gui.addNewImage(imageback, "local");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void toggleWebcam(){
        if(webcam.isOpen()){
            webcam.close();
        }else{
            webcam.open();
        }
    }
}
