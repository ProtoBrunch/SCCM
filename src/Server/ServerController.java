package Server;


import java.net.Socket;

import static Server.ServerListener.chatRoomAdresses;

/**
 * Zuständig für die Verarbeitung der vom ServerListener übergebenen Kommandos.
 *
 * Created by Robin Berberat on 04.04.2017.
 */
class ServerController {
    /**
     * Konstruktor des ServerControllers
     * Bricht den Input in einen String auf, analysiert dessen erste Zelle und führt dann die dazu passende Anweisung aus.
     *
     * @param input
     * @param client
     * @throws Exception
     */
    ServerController(String input, Socket client) throws Exception {
        String[] stringArray = input.split(" ");
        switch(stringArray[0].toUpperCase()) {
            case "SH":
                new ServerWriter(client).stringToClient(
                        "Hallo Benutzer. Was sollen sie machen? [CNR] für einen neuen Chatraum," +
                                " [SMC] um sich die bestehenden CHats anzeigen zu lassen. ");
                break;
            case "CNR":
                new ServerWriter(client).stringToClient("RCI");
                break;
            case "SCI":
                String adressOfRoom = client.getInetAddress().toString().substring(1);
                chatRoomAdresses.put(stringArray[1], adressOfRoom);
                new ServerWriter(client).stringToClient("NRM Added you to the list of Chatrooms.");
                break;
            case "SMC":
                new ServerWriter(client).showOpenChatRooms();
                new ServerWriter(client).stringToClient("AFS Which one of these rooms do you wish to join?");
                break;
            case "SS":
                String adress = chatRoomAdresses.get(stringArray[1]);
                chatRoomAdresses.remove(stringArray[1]);
                new ServerWriter(client).stringToClient("CTS "+adress);
                break;
            default:
                System.out.println(input);
                new ServerWriter(client).stringToClient("EM");
                break;
        }
    }
}

