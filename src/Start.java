import Client.ChatGui;
import Client.WebcamChatGui;

/**
 * Created by meiersila on 04.04.2017.
 */
public class Start {
    public static void main(String[] args) {
       /* ChatGui cg = new ChatGui();
        cg.setComponents();*/
        WebcamChatGui wcg = new WebcamChatGui();
        wcg.setComponents();
    }
}
