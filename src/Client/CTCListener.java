package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by berberatr on 04.04.2017.
 */
public class CTCListener extends Thread{
    Socket client;
    BufferedReader inFromClient;

    public CTCListener(Socket client) throws IOException {
        this.client = client;
        inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void run(){
        String input;
        try {
            while(true){
                input = inFromClient.readLine();
                System.out.println(input);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
