package Client;

import Server.ServerWriter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by User on 04.04.2017.
 */
public class ClientController {
    public ClientController(String input, Socket server) throws IOException {
        String[] stringArray = input.split(" ");
        switch(stringArray[0].toUpperCase()){
            case "RCI":
                System.out.println("Server wishes to know Information for the new Chatroom.");
                new ClientWriter(server).sendChatRoomInformation();
                break;


            case "ONS":
                System.out.println("Client opens new Server.");
                new ClientServer(stringArray[1]).start();
                break;

            case "AFS":
                System.out.println(input.substring(4));
                new ClientWriter(server).sendSelection();
                break;

            case "CTS":
                System.out.println("Received Information");
                new ClientToClientConnection(stringArray[1], Integer.parseInt(stringArray[2])).start();
                break;

            case "NRM": //These messages expect no answer to return.
                System.out.println(input.substring(4));
                break;

            case "CC":
                server.close();
                break;

            default:
                System.out.println(input);
                new ClientWriter(server).defaultMessage();
                break;
        }
    }
}

