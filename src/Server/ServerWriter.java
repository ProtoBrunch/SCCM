package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static Server.ServerListener.chatRoomPorts;

/**
 * Created by User on 04.04.2017.
 */
public class ServerWriter {
    Socket client;
    PrintWriter outToClient;
    Scanner scanner;
    String string;


    ServerWriter(Socket client) throws IOException {
        this.client = client;
        outToClient = new PrintWriter(client.getOutputStream(),true);
        scanner = new Scanner(System.in);
    }

    public void defaultMessage(){
        string = scanner.nextLine();
        outToClient.println(string);
    }

    public void sayHello(){
        outToClient.println("Hallo Benutzer. Was sollen sie machen? [CNR] für einen neuen Chatraum, [SMC] um sich die bestehenden CHats anzeigen zu lassen. ");
    }

    public void showOpenChatRooms() {
        outToClient.println("NRM These Rooms are currently open:");
        for(String name : chatRoomPorts.keySet()){
            outToClient.println("NRM "+name);
        }
    }

    public void requestChatRoomInformation() {
        outToClient.println("RCI");
    }

    public void addedClientToRoomList() {
        outToClient.println("NRM Added you to the list of Chatrooms.");
    }

    public void askForSelection() {
        outToClient.println("AFS Which one of these rooms do you wish to join?");
    }

    public void sendRoomInformation(String adress, int port) {
        outToClient.println("CTS "+adress+" "+port);
    }

    public void disconnectingClient() {
        outToClient.println("CC disconnecting you now from server.");
    }
}
