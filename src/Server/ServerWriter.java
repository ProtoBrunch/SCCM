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
class ServerWriter {
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
     * Zeigt dem Client alle erstellen Räume an.
     */
    void showOpenChatRooms() {
        outToClient.println("NRM These Rooms are currently open:");
        for(String name : chatRoomAdresses.keySet()){
            outToClient.println("NRM "+name);
        }
    }

    /**
     * Sendet Nachricht and Client
     * @param message String, welcher verschickt werden soll.
     */
    void stringToClient(String message){
        outToClient.println(message);
    }
}
