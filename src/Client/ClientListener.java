package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by User on 04.04.2017.
 */
public class ClientListener extends Thread{
    Socket server;
    BufferedReader inFromServer;

    public ClientListener(Socket server) throws IOException {
        this.server = server;
        inFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    public void run(){
        try{
            while(server.isConnected()) {
                String input = inFromServer.readLine();
                new ClientController(input, server);
            }


        } catch(SocketException se){
            try {
                inFromServer.close();
                System.out.println("Closed inputStream");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Closed Thread");
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
            this.interrupt();
        }

    }
}
