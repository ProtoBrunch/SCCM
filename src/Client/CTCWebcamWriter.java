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
    Webcam webcam;
    DataOutputStream outToClient;
    WebcamChatGui gui;
    BufferedImage webcamImage;
    ByteArrayOutputStream baos;



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
        while (true){
            try {
                webcamImage = webcam.getImage();
                baos = new ByteArrayOutputStream();
                ImageIO.write(webcamImage,"png", baos);
                baos.flush();
                byte[] data = baos.toByteArray();
                baos.close();
                outToClient.writeInt(data.length);
                outToClient.write(data);
                BufferedImage imageback = ImageIO.read(new ByteArrayInputStream(data));
                gui.addNewImage(imageback,"local");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
