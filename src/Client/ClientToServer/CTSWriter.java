package Client.ClientToServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client-To-Server Writer
 *
 * Ausgangspunkt aller Anweisungen die der Client an den Server schickt.
 *
 * Created by Robin Berberat on 04.04.2017.
 */
public class CTSWriter {
    private  Socket server;
    private  PrintWriter outToServer;
    private  Scanner scanner;


    /**
     * Konstruktor
     *
     * @param client
     * @throws IOException
     */
    CTSWriter(Socket client) throws IOException {
        this.server = client;
        outToServer = new PrintWriter(client.getOutputStream(),true);
        scanner = new Scanner(System.in);
    }

    /**
     * Bittet den Benutzer um eine Eingabe, welche dann an den Server geschickt wird.
     */
    void defaultMessage(){
        String string = scanner.nextLine();
        outToServer.println(string);
    }

    /**
     * Fragt den Benutzer, unter welchem Namen und Port er seinen Chatraum erstellen möchte.
     * Nach Abfrage wird über den Controller ein Thread mit ServerSocket erstellt und die Informationen an den Server gesendet.
     *
     * @throws IOException
     */
    void sendChatRoomInformation() throws IOException {
        System.out.println("What's the name of the room?");
        String roomName = scanner.nextLine();
        new CTSController("ONS ", server);
        outToServer.println("SCI "+roomName);
    }

    /**
     * Erlaubt es dem Benutzer, dem Server mitzuteilen, welchem Chatraum er beitreten möchte.
     */
    void sendSelection() {
        String selection = scanner.nextLine();
        outToServer.println("SS "+selection);
    }

    public void errorMessage() {
        System.out.println("Your input was invalid! Please try again.");
        String string = scanner.nextLine();
        outToServer.println(string);
    }
}
