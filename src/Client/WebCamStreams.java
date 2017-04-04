package Client;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamStreamer;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import javax.swing.*;
import java.net.MalformedURLException;

/**
 * Created by meiersila on 04.04.2017.
 */
public class WebCamStreams {
    private String ipAdress;
    private int port = 50005;
    static {
        Webcam.setDriver(new IpCamDriver());
    }

    public WebCamStreams(String ipAdress){
        this.ipAdress = ipAdress;
    }

    public void startWebcamStream(Webcam webcam){
        WebcamStreamer ws = new WebcamStreamer(port, webcam, 30,true );
        ws.start();
    }

    public JPanel getLocalWebcam() throws MalformedURLException {
        IpCamDeviceRegistry.register("Localhost", "http://localhost:"+port, IpCamMode.PUSH);

        WebcamPanel panel = new WebcamPanel(Webcam.getWebcams().get(0));
        panel.setFPSLimit(1);

        return panel;
    }

    public JPanel getExternalWebcam()throws MalformedURLException {
        IpCamDeviceRegistry.register("ExterneCam", "http://"+ipAdress+":"+port, IpCamMode.PUSH);

        WebcamPanel panel = new WebcamPanel(Webcam.getWebcams().get(0));
        panel.setFPSLimit(1);

        return panel;
    }
}
