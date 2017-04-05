package Client;

import Client.ClientToClient.CTCTextWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by meiersila on 30.03.2017.
 * WebcamChatGui welches sich beim Verbinden mit einem anderen Client öffnet.
 */
public class WebcamChatGui implements ActionListener, KeyListener {
    private Socket client;

    private JFrame frame;
    private JPanel panel_outer;
    private JPanel panel_1;
    private JPanel panel_2;

    private JLabel label_1_1;
    private JLabel label_1_2;
    private JPanel panel_2_2;

    private JScrollPane scrollPane;

    private JPanel messagePanel;

    private JTextArea messageTextArea;
    private JButton messageSendButton;

    /**
     * Konstruktor. Alle benötigten Komponenten werden initialisiert.
     */
    public WebcamChatGui(Socket client){
        this.client = client;

        frame = new JFrame("Skipe - WebCam");
        panel_outer = new JPanel(new GridLayout(1,2));
        panel_1 = new JPanel();
        panel_2 = new JPanel(new BorderLayout());

        label_1_1 = new JLabel();

        label_1_2 = new JLabel();

        panel_2_2 = new JPanel();

        messagePanel = new JPanel();
        messagePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        messageSendButton = new JButton("Senden");
        messageSendButton.addActionListener(this);
        messageTextArea = new JTextArea(2,10);
        messageTextArea.addKeyListener(this);

        scrollPane = new JScrollPane(messagePanel);
    }

    /**
     * Wird aufgerufen, um das Gui anzuzeigen und die Komponenten ins Frame zusetzen.
     */
    public void setComponents(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());

        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS));
        panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel_1.setLayout(new GridLayout(2,1));

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 300));

        panel_1.add(label_1_1);
        panel_1.add(label_1_2);

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
            reactToListener();
        }
    }

    /**
     * Keylistener, sobald der Key gedrückt wird, wird der Text geschickt und das Textfeld geleert.
     * @param e KeyEvent
     */
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
           reactToListener();
        }
    }

    /**
     * Keylistener benötigt diese Methode
     * @param e
     */
    public void keyTyped(KeyEvent e){}

    /**
     * Keylistener, sobald der key losgelassen wird, wird das Textfeld geleert.
     * @param e
     */
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            messageTextArea.setText("");
        }
    }

    /**
     * Autoscroller, damit neue Nachrichten direkt angezeigt werden.
     */
    private void scrollToBottom(){
        int height = messagePanel.getHeight();
        Rectangle rect = new Rectangle(0,height,10,10);
        messagePanel.scrollRectToVisible(rect);
    }

    private void reactToListener(){
        addNewMessage(messageTextArea.getText());
        try {
            new CTCTextWriter(client , messageTextArea.getText()).start();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        messageTextArea.setText("");
    }

    /**
     * Neue Nachrichten werden im messagePanel angezeigt
     * @param message Nachricht, welche angezeigt werden soll.
     */
    public void addNewMessage(String message){
        if(!message.equals("")){
            JLabel newMessageLabel = new JLabel(message);
            messagePanel.add(newMessageLabel);
            SwingUtilities.updateComponentTreeUI(scrollPane);
            scrollToBottom();
        }
    }

    /**
     * JLabel SetIcon wird verwendet, um das Webcambild anzuzeigen.

     * @param externOrLocal bei welchem Label das Bild angezeigt werden soll.
     */
    public void addNewImage(BufferedImage bufferedImage, String externOrLocal){
        switch(externOrLocal){
            case "extern":
                label_1_1.setIcon(new ImageIcon(bufferedImage));
                break;
            case "local":
                label_1_2.setIcon(new ImageIcon(bufferedImage));
                break;
            default:
                label_1_2.setIcon(new ImageIcon(bufferedImage));
                break;
        }
    }
}
