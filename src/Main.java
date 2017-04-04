import Client.ThreadWebcam;
import Client.WebcamChatGui;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;

/**
 * Created by meiersila on 04.04.2017.
 */
public class Main {

    public static void main(String[] args) {

        Webcam webcam = Webcam.getDefault();
        new ThreadWebcam(webcam).start();

        WebcamChatGui wcg = new WebcamChatGui(webcam, "172.16.2.156");
        wcg.setComponents();
    }
}
