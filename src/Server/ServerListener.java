package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

/**
 * Created by User on 04.04.2017.
 */
public class ServerListener extends Thread{
    private Socket client;
    private BufferedReader inFromClient;
    static HashMap<String, String> chatRoomAdresses = new HashMap<>();

    ServerListener(Socket client) throws IOException {
        this.client = client;
        inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void run(){
        try {
            new ServerController("SH",client);
            while(client.isConnected()) {
                String input = inFromClient.readLine();
                new ServerController(input, client);
            }

        } catch(SocketException se){
            try {
                inFromClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
            this.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

