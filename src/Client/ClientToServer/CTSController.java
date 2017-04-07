package Client.ClientToServer;

import Client.ClientToClient.ClientToClientConnection;

import java.io.IOException;
import java.net.Socket;

/**
 * Client-To-Server Controller
 * Die Verwaltung des Client-Listeners.
 * Verarbeitet die an ihn vom CTSListener übergebenen Anweisungen, und startet dazu korrespondierende Methoden anderer Klassen.
 *
 * @see CTSListener
 * @see CTSWriter
 * @see ClientToClientConnection
 *
 * Created by Robin Berberat on 04.04.2017.
 */
public class CTSController {

    /**
     * Konstruktor für den CTSController
     *
     * @param input
     * @param server
     * @throws IOException
     */
    CTSController(String input, Socket server) throws IOException {
        String[] stringArray = input.split(" ");
        switch(stringArray[0].toUpperCase()){
            case "RCI":
                new CTSWriter(server).sendChatRoomInformation();
                break;
            case "ONS":
                new ClientToClientConnection("localhost",true).start();
                break;
            case "AFS":
                System.out.println(input.substring(4));
                new CTSWriter(server).sendSelection();
                break;
            case "CTS":
                System.out.println(stringArray[1]);
                new ClientToClientConnection(stringArray[1],false).start();
                break;
            case "NRM": //These messages expect no answer to return.
                System.out.println(input.substring(4));
                break;
            case "CC":
                server.close();
                break;
            case "EM":
                new CTSWriter(server).errorMessage();
                break;
            default:
                System.out.println(input);
                new CTSWriter(server).defaultMessage();
                break;
        }
    }
}

