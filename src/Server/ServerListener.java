package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by User on 04.04.2017.
 */
public class ServerListener extends Thread{
    Socket client;
    BufferedReader inFromClient;

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

        while(client.isConnected()){
            try {
                String input = inFromClient.readLine();
                System.out.println("Nachricht erhalten");
                new ServerController(input, client);

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
            }
        }
    }
}
