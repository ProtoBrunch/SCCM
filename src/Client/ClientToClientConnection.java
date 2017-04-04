package Client;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * Created by berberatr on 04.04.2017.
 */
public class ClientToClientConnection extends Thread{
    String host;
    int port;
    Socket client = null;

    public ClientToClientConnection(String adress, int port) {
        this.host = adress;
        this.port = port;
    }

    public void run() {
        try {
            client = new Socket(host, port);
            System.out.println("verbunden");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            new CTCListener(client).start();
            System.out.println("listener start");
            new CTCWriter(client).start();
            System.out.println("writer start");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
