package Client;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamStreamer;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import java.net.MalformedURLException;

/**
 * Created by meiersila on 04.04.2017.
 */
public class ThreadWebcam extends Thread {
    private Webcam webcam;
    public ThreadWebcam(Webcam webcam){
        this.webcam = webcam;
    }
    public void run(){
        new WebcamStreamer(50004, webcam, 30, true);
        System.out.println("Thread Webcam Ready");
        while (true);
    }
}
