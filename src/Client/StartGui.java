package Client;

import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * Wird in Main geöffnet. Erstellt neue Chats oder Webcamübertragungen.
 * Created by meiersila on 30.03.2017.
 */
public class StartGui implements ActionListener{
    private JFrame frame;
    private JPanel panel_0;
    private JPanel panel_1_0;
    private JLabel infoLabel;
    private JPanel panel_0_0;
    private JPanel panel_0_1;
    private JPanel panel_0_2;
    private JPanel panel_0_3;
    private JPanel panel_0_4;
    private JPanel panel_0_5;
    private JPanel panel_0_6;
    private JPanel panel_0_7;
    private JPanel panel_0_8;
    private JLabel ipLabel;
    private JTextArea ipTextArea;
    private JLabel portLabel;
    private JTextArea portTextArea;
    private JLabel userNameLabel;
    private JTextArea userNameTextArea;
    private JButton chatButton;
    private JButton webcamChatButton;

    /**
     * Konstruktor der Klasse. Initiert alle Swing Komponenten
     */
    public StartGui(){
        frame = new JFrame("Skipe");
        panel_0 = new JPanel();

        infoLabel = new JLabel();
        panel_1_0 = new JPanel();
        panel_0_0 = new JPanel();
        panel_0_1 = new JPanel();
        panel_0_2 = new JPanel();
        panel_0_3 = new JPanel();
        panel_0_4 = new JPanel();
        panel_0_5 = new JPanel();
        panel_0_6 = new JPanel();
        panel_0_7 = new JPanel();
        panel_0_8 = new JPanel();

        ipLabel = new JLabel("IP angeben");
        ipTextArea = new JTextArea(1,10);
        portLabel = new JLabel("Port angeben");
        portTextArea = new JTextArea(1,10);
        userNameLabel = new JLabel("Username angeben");
        userNameTextArea = new JTextArea(1,10);
        chatButton = new JButton("Chatraum");
        webcamChatButton = new JButton("Webcam Chatraum");
    }

    /**
     * Das Frame wird geöffnet und alle Swing Komponenten entsprechend hinzugefügt.
     */
    public void setComponents(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,550);

        panel_0.setLayout(new GridLayout(1,2));

        panel_0_0.setLayout(new GridLayout(4,2));

        panel_0_1.add(ipLabel);
        panel_0_2.add(ipTextArea);
        panel_0_3.add(portLabel);
        panel_0_4.add(portTextArea);
        panel_0_5.add(userNameLabel);
        panel_0_6.add(userNameTextArea);
        panel_0_7.add(chatButton);
        chatButton.addActionListener(this);
        panel_0_8.add(webcamChatButton);
        webcamChatButton.addActionListener(this);

        panel_0_0.add(panel_0_1);
        panel_0_0.add(panel_0_2);
        panel_0_0.add(panel_0_3);
        panel_0_0.add(panel_0_4);
        panel_0_0.add(panel_0_5);
        panel_0_0.add(panel_0_6);
        panel_0_0.add(panel_0_7);
        panel_0_0.add(panel_0_8);

        panel_1_0.add(infoLabel);

        panel_0.add(panel_0_0);
        panel_0.add(panel_1_0);


        frame.add(panel_0);

        frame.setVisible(true);
    }

    /**
     * ActionListener ruft diese Methode.
     * @param e Event welcher mitgeschickt wird.
     */
    public void actionPerformed(ActionEvent e) {
        String ipadress = ipTextArea.getText();
        String portString = portTextArea.getText();
        String username = userNameTextArea.getText();

        if(!checkIpAdress(ipadress)) {
            ipTextArea.setText("");
            infoLabel.setText("Inkorrete IP angegeben! Bitte anpassen");

        }else if(!checkPort(portString)){
            portTextArea.setText("");
            infoLabel.setText("Inkorrekter Port angegeben! Bitte anpassen");
        }
        if(checkPort(portString) && checkIpAdress(ipadress)) {
            infoLabel.setText("Verbindung wird aufgebaut...");

            if(e.getSource() == webcamChatButton){
                WebcamChatGui wcg = new WebcamChatGui(Webcam.getDefault(),"172.16.2.156");
                wcg.setComponents();

                disposeFrame();

            }else if(e.getSource() == chatButton){
                ChatGui cg = new ChatGui();
                cg.setComponents();
                disposeFrame();
            }
        }
    }

    /**
     * Überprüft die IPv4 mittels Regex
     * @param ip Ip-adresse als String
     * @return True falls die Eingabe eine IP ist.
     */
    private boolean checkIpAdress(String ip){
        if(Pattern.matches("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)", ip)){
            System.out.println("ip correct");
            return true;
        }else{
            System.out.println("ip incorrect");
            return false;
        }
    }

    /**
     * Überprüft den Port mittels Regex
     * @param port Port als String
     * @return True falls der Port eine valide Eingabe ist.
     */
    private boolean checkPort(String port){
        if(Pattern.matches("\\d{1,5}", port)){
            System.out.println("port correct");
            return true;
        }else{
            System.out.println("port incorrect");
            return false;
        }
    }

    /**
     * Schliesst das StartGui-Fenster.
     */
    private void disposeFrame(){
        this.frame.dispose();
    }
}
