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
                processWebcamimage();
            }
        }
    }

    private void processWebcamimage(){
        try {
            BufferedImage webcamImage = webcam.getImage();
            byte[] imageArray = turnBufferedImagetoByteArray(webcamImage);
            sendImageToClient(imageArray);
            sendImageToGui(imageArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] turnBufferedImagetoByteArray(BufferedImage buffImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(buffImage, "png", baos);
        baos.flush();
        byte[] imageArray = baos.toByteArray();
        baos.close();
        return imageArray;
    }

    private void sendImageToClient(byte[] imageArray) throws IOException {
        outToClient.writeInt(imageArray.length);
        outToClient.write(imageArray);
    }

    private void sendImageToGui(byte[] imageArray) throws IOException {
        BufferedImage imageback = ImageIO.read(new ByteArrayInputStream(imageArray));
        gui.addNewImage(imageback, "local");
    }
}
