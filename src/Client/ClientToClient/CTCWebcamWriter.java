package Client.ClientToClient;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;


/**
 * Klasse welche ein Webcambild an einen andern Client schickt.
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamWriter extends Thread{
    private Socket client;
    private Webcam webcam;
    private  DataOutputStream outToClient;
    private Client.WebcamChatGui gui;

    /**
     * Konkstruktor
     * @param client Client, welcher das Bild schickt
     * @param webcam Webcam, von welcher das BIld genommen werden soll
     * @param gui Gui, auf welchem das Bild lokal dargestellt wird.
     * @throws IOException
     */
    public CTCWebcamWriter(Socket client, Webcam webcam, Client.WebcamChatGui gui) throws IOException {
        this.client = client;
        this.webcam = webcam;
        this.gui = gui;
        if(!webcam.isOpen()){
            webcam.open();
        }
        outToClient = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
    }

    /**
     * Thread start
     */
    public void run(){
        while (client.isConnected()){
            while(webcam.isOpen()) {
                processWebcamimage();
            }
        }
    }

    /**
     * List ein Bild von der Webcam und verschickt es als Bytearray an den Client und als BufferedImage ans lokale GUI
     */
    private void processWebcamimage(){
        try {
            BufferedImage webcamImage = webcam.getImage();
            byte[] imageArray = turnBufferedImagetoByteArray(webcamImage);
            sendImageToClient(imageArray);
            sendImageToGui(webcamImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Konvertiert das BufferedImage zu einem Bytearray, damit dieser verschickt werden kann.
     * @param buffImage Image welches umgewandlet werdne soll.
     * @return Image in Form vom Bytearray
     * @throws IOException
     */
    private byte[] turnBufferedImagetoByteArray(BufferedImage buffImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(buffImage, "png", baos);
        baos.flush();
        byte[] imageArray = baos.toByteArray();
        baos.close();
        return imageArray;
    }

    /**
     * Die länge des Byte-Array sowie der Array werden an den anderen Client geschickt. Die Länge ist zum erstellen des Arrays auf der anderen Seite wichtig.
     * @param imageArray
     * @throws IOException
     */
    private void sendImageToClient(byte[] imageArray) throws IOException{
        outToClient.writeInt(imageArray.length);
        outToClient.write(imageArray);
    }

    /**
     * Zeigt das Image auf dem Gui an.
     * @param bufferedImage Webcambild welches angezeigt werdne soll.
     * @throws IOException
     */
    private void sendImageToGui(BufferedImage bufferedImage) throws IOException {
        gui.addNewImage(bufferedImage, "local");
    }
}
