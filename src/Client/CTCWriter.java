package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by berberatr on 04.04.2017.
 */
public class CTCWriter extends Thread{
    Socket client;
    PrintWriter outToClient;
    Scanner scanner;
    String string;

    public CTCWriter(Socket client) throws IOException {
        this.client = client;
        outToClient = new PrintWriter(client.getOutputStream(),true);
        scanner = new Scanner(System.in);
    }

    public void run(){
        while(true){
            string = scanner.nextLine();
            outToClient.println(string);
        }
    }
}
