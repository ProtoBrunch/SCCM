package Client;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamStreamer;

/**
 * Created by meiersila on 04.04.2017.
 */
public class WebCamStreamThread extends Thread {
    private int port;
    private Webcam webcam;

    public WebCamStreamThread(int port, Webcam webcam){
        this.port = port;
        this.webcam = webcam;
    }

    public void run(){
        new WebcamStreamer(port, webcam, 30, true);

        while (true);
    }
}
