package Client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by User on 04.04.2017.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String host = "172.16.2.156";
        int port = 50000;
        Socket server = null;

        try {
            server = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new ClientListener(server).start();
    }
}
