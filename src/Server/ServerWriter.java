package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static Server.ServerListener.chatRoomAdresses;

/**
 * ServerWriter welcher Nachrichten und Bits an Clients schickt.
 * Created by User on 04.04.2017.
 */
public class ServerWriter {
    private PrintWriter outToClient;

    /**
     * Konstruktor, erstellt ein neues PrintWriterobjekt und Scanner.
     * @param client Client, auf welchen geschrieben werden soll
     * @throws IOException
     */
    ServerWriter(Socket client) throws IOException {
        outToClient = new PrintWriter(client.getOutputStream(),true);
    }

    /**
     * Schickt Wert "EM" an den Client, wird im Switch statement ausgewertet.
     */
    void errorMessage(){
        outToClient.println("EM");
    }

    /**
     * Schickt Nachricht an Client, und erwartet Input.
     */
    void sayHello(){
        outToClient.println("Hallo Benutzer. Was sollen sie machen? [CNR] für einen neuen Chatraum, [SMC] um sich die bestehenden CHats anzeigen zu lassen. ");
    }

    /**
     * Zeigt dem Client alle erstellen Räume an.
     */
    void showOpenChatRooms() {
        outToClient.println("NRM These Rooms are currently open:");
        for(String name : chatRoomAdresses.keySet()){
            outToClient.println("NRM "+name);
        }
    }

    void requestChatRoomInformation() {
        outToClient.println("RCI");
    }

    void addedClientToRoomList() {
        outToClient.println("NRM Added you to the list of Chatrooms.");
    }

    void askForSelection() {
        outToClient.println("AFS Which one of these rooms do you wish to join?");
    }

    void sendRoomInformation(String adress) {
        outToClient.println("CTS "+adress);
    }

    public void disconnectingClient() {
        outToClient.println("CC disconnecting you now from server.");
    }

    /**
     * Sendet Nachricht and Client
     * @param message String, welcher verschickt werden soll.
     */
    void stringToClient(String message){
        outToClient.println(message);
    }
}
