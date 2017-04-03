package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by User on 04.04.2017.
 */
public class ClientWriter {
    Socket server;
    PrintWriter outToServer;
    Scanner scanner;
    String string;


    ClientWriter(Socket client) throws IOException {
        this.server = client;
        outToServer = new PrintWriter(client.getOutputStream(),true);
        scanner = new Scanner(System.in);
    }

    public void defaultMessage(){
        string = scanner.nextLine();
        outToServer.println(string);
    }
}
