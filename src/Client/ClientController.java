package Client;

import Server.ServerWriter;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by User on 04.04.2017.
 */
public class ClientController {
    public ClientController(String input, Socket server) throws IOException {
        switch(input){
            default:
                System.out.println(input);
                new ClientWriter(server).defaultMessage();
        }
    }
}

