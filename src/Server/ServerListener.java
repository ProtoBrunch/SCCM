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
    Socket client;
    BufferedReader inFromClient;
    static HashMap<String, Integer> chatRoomPorts = new HashMap<>();
    static HashMap<String, String> chatRoomAdresses = new HashMap<>();

    public ServerListener(Socket client) throws IOException {
        this.client = client;
        inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void run(){
        try {
            new ServerWriter(client).sayHello();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while(client.isConnected()) {
                String input = inFromClient.readLine();
                System.out.println("Nachricht erhalten");
                new ServerController(input, client);
            }

        } catch(SocketException se){
            try {
                inFromClient.close();
                System.out.println("Closed inputStream");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Closed Thread");
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
            this.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
