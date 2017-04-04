package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static Server.ServerListener.chatRoomAdresses;
import static Server.ServerListener.chatRoomPorts;

/**
 * Created by User on 04.04.2017.
 */
public class ServerController {
    public ServerController(String input, Socket client) throws Exception {
        String[] stringArray = input.split(" ");
        switch(stringArray[0].toUpperCase()) {
            case "CNR":
                System.out.println("Client wishes to create new Chatroom.");
                new ServerWriter(client).requestChatRoomInformation();
                break;

            case "SCI":
                System.out.println("Filling the gained Information into Hashmaps.");
                chatRoomPorts.put(stringArray[1], Integer.parseInt(stringArray[2]));
                String adressOfRoom = client.getInetAddress().toString().substring(1);
                chatRoomAdresses.put(stringArray[1], adressOfRoom);
                new ServerWriter(client).addedClientToRoomList();
                break;

            case "SMC":
                System.out.println("Client requests all open Chatrooms.");
                new ServerWriter(client).showOpenChatRooms();
                new ServerWriter(client).askForSelection();
                break;

            case "SS":
                System.out.println("Sending Client Room Information");
                int port = chatRoomPorts.get(stringArray[1]);
                chatRoomPorts.remove(stringArray[1]);
                String adress = chatRoomAdresses.get(stringArray[1]);
                chatRoomAdresses.remove(stringArray[1]);
                new ServerWriter(client).sendRoomInformation(adress,port);
                //new ServerWriter(client).disconnectingClient();
                break;

            default:
                System.out.println(input);
                new ServerWriter(client).defaultMessage();
                break;
        }
    }
}

