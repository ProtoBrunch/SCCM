package Client;

import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

/**
 * Created by meiersila on 30.03.2017.
 * WebcamChatGui welches sich beim Verbinden mit einem anderen Client öffnet.
 */
public class WebcamChatGui implements ActionListener {
    private JFrame frame;
    private JPanel panel_outer;
    private JPanel panel_1;
    private JPanel panel_2;

    private JPanel panel_1_1;
    private JPanel panel_1_2;
    private JPanel panel_2_2;

    private JScrollPane scrollPane;

    private JPanel messagePanel;

    private JTextArea messageTextArea;
    private JButton messageSendButton;

    /**
     * Konstruktor. Alle benötigten Komponenten werden initialisiert. Für den webcamPanel wird der Parameter verwendet
     */
    public WebcamChatGui(){
        frame = new JFrame("Skipe - WebCam");
        panel_outer = new JPanel(new GridLayout(1,2));
        panel_1 = new JPanel();
        panel_2 = new JPanel(new BorderLayout());

        panel_1_1 = new JPanel();
        panel_1_2 = new JPanel();
        panel_2_2 = new JPanel();

        messagePanel = new JPanel();
        messagePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        messageSendButton = new JButton("Senden");
        messageSendButton.addActionListener(this);
        messageTextArea = new JTextArea(2,10);

        scrollPane = new JScrollPane(messagePanel);
    }

    /**
     * Wird aufgerufen, um das Gui anzuzeigen und die Komponenten im Frame zusetzen.
     */
    public void setComponents(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());

        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS));
        panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.PAGE_AXIS));

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 300));

        WebCamStreams wcs = new WebCamStreams("localhost");
        Webcam webcam = Webcam.getDefault();
        wcs.startWebcamStream(webcam);
        try {
            panel_1_1 = wcs.getExternalWebcam();
            panel_1_2 = wcs.getLocalWebcam();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        panel_2_2.add(messageTextArea);
        panel_2_2.add(messageSendButton);
        panel_2.add(scrollPane,"Center");
        panel_2.add(panel_2_2, "South");
        panel_outer.add(panel_1, "West");
        panel_outer.add(panel_2,"Center");
        panel_outer.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.add(panel_outer);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Reaktion auf Buttongedrückt.
     * @param e Event, in diesem Fall vom Button
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == messageSendButton){
            JLabel messageLabel = new JLabel(messageTextArea.getText());
            messagePanel.add(messageLabel);
            messageTextArea.setText("");
            SwingUtilities.updateComponentTreeUI(scrollPane);
            scrollToBottom();
        }
    }

    private void scrollToBottom(){
        int height = messagePanel.getHeight();
        Rectangle rect = new Rectangle(0,height,10,10);
        messagePanel.scrollRectToVisible(rect);
    }

    /**
     * Ändert das Icon vom JLabel webcamPanel.
     * @param newImage ImageIcon welches vom Bytearray berechnet wurde.
     * @param location Wovon das Bild kommt "server" für externes Bild, "client" für das eigene
     */
   /* public void changeWebcamImageIcon(ImageIcon newImage, String location){
        switch(location){
            case "server":
                this.panel_1_2.setIcon(newImage); //Webcam Image from other client
                break;
            case "client":
                this.webcamPanel_0_0_1.setIcon(newImage); //Local Webcam Image
                break;
        }

    }*/
}
