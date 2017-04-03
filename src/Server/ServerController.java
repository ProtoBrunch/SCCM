package Server;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by User on 04.04.2017.
 */
public class ServerController {
    public ServerController(String input, Socket client) throws IOException {
        switch(input){
            default:
                System.out.println(input);
                new ServerWriter(client).defaultMessage();
        }
    }
}
