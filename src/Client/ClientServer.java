package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by berberatr on 04.04.2017.
 */
public class ClientServer extends Thread{
    int port;
    Socket client = null;

    public ClientServer(String s) {
        this.port = Integer.parseInt(s);
    }

    public void run(){
        try(ServerSocket clientServer = new ServerSocket(port)) {
            System.out.println("Server gestartet und wartet auf Client.");
            client = clientServer.accept();

            new CTCListener(client).start();
            new CTCWriter(client).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
