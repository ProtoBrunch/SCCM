package Client.ClientToClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

/**
 * Thread, welcher ständig das Webcambild des anderen Client erhält.
 * Created by berberatr on 05.04.2017.
 */
public class CTCWebcamListener extends Thread{
    private Socket client;
    private Client.WebcamChatGui gui;
    private DataInputStream inFromClient;

    /**
     * Konstruktor
     * @param client Client welcher das Bild erhält
     * @param gui Gui, auf welchem das Bild soll dargestellt werden.
     * @throws IOException
     */
    public CTCWebcamListener(Socket client, Client.WebcamChatGui gui) throws IOException{
        this.client = client;
        this.gui = gui;
        inFromClient = new DataInputStream(new BufferedInputStream(client.getInputStream()));
    }

    /**
     * Thread Methode, welche beim Aufruf ".start()" läuft.
     */
    public void run(){
        try{
            readFromInputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Solange die Verbindung zwischen den Clients steht, wird die Methode aufgerufen
     * Nimmt einen Bytearray entgegen, setzt ihn zum BufferedImage zusammen und zeigt es auf dem Gui an.
     * @throws IOException
     */
    private void readFromInputStream() throws IOException {
        while(client.isConnected()){
            int length = inFromClient.readInt();
            if(length > 0) {
                byte[] imageByteArray = new byte[length];
                inFromClient.readFully(imageByteArray, 0, length);
                BufferedImage imageback = ImageIO.read(new ByteArrayInputStream(imageByteArray));
                gui.addNewImage(imageback, "extern");
            }
        }
    }
}
