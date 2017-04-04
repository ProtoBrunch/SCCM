package Client;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import javax.swing.*;
import java.net.MalformedURLException;

/**
 * Created by meiersila on 04.04.2017.
 */
public class WebCamStreams {
    static {
        Webcam.setDriver(new IpCamDriver());
    }
    private String ipAdress;
    private int port = 50005;
    private Webcam webcam;

    public WebCamStreams(String ipAdress, Webcam webcam){
        this.ipAdress = ipAdress;
        this.webcam = webcam;
    }
    public JPanel getExternalWebcam()throws MalformedURLException {
        IpCamDeviceRegistry.register("ExterneCam", "http://172.16.2.156:50005", IpCamMode.PUSH);

        System.out.println(Webcam.getWebcams().size());
        WebcamPanel panel = new WebcamPanel(Webcam.getWebcams().get(Webcam.getWebcams().size()-1));
        panel.setFPSLimit(1);

        return panel;
    }

    public JPanel getLocalWebcam() throws MalformedURLException {
        IpCamDeviceRegistry.register("Localost", "http://172.16.2.137:50004", IpCamMode.PUSH);

        WebcamPanel panel = new WebcamPanel(Webcam.getWebcams().get(Webcam.getWebcams().size()-1));
        panel.setFPSLimit(1);

        return panel;
    }


}
