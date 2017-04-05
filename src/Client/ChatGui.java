package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Chatgui um nur zu chatten.
 * Created by meiersila on 30.03.2017.
 */
public class ChatGui implements ActionListener, KeyListener {
    private String username;
    private JFrame frame;
    private JPanel panel_outer;
    private JPanel panel_1;
    private JPanel panel_2;

    private JPanel panel_2_2;

    private JScrollPane scrollPane;

    private JPanel messagePanel;

    private JTextArea messageTextArea;
    private JButton messageSendButton;

    /**
     * Konstruktor. Alle benötigten Komponenten werden initialisiert.
     */
    public ChatGui(String username){
        this.username = username;

        frame = new JFrame("Skipe");
        panel_outer = new JPanel(new BorderLayout());
        panel_1 = new JPanel();
        panel_2 = new JPanel(new BorderLayout());
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
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.PAGE_AXIS));

        addNewUserName("Aktuelle User:");

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(300, 300));

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
     * Actionlistener, welcher Nachrichten sendet.
     * @param e Event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == messageSendButton){
            addNewMessage(messageTextArea.getText());
            messageTextArea.setText("");
            System.out.println("Button clicked!");
        }
    }

    /**
     * Neue Nachrichten werden im messagePanel angezeigt
     * @param message Nachricht, welche angezeigt werden soll.
     */
    public void addNewMessage(String message){
        if(message.equals(""));
        else {
            message = username +": " + message;
            JLabel newMessageLabel = new JLabel(message);
            messagePanel.add(newMessageLabel);
            SwingUtilities.updateComponentTreeUI(scrollPane);
            scrollToBottom();
        }
    }

    /**
     * Keylistener, sobald der Key gedrückt wird, wird der Text geschickt und das Textfeld geleert.
     * @param e KeyEvent
     */
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            addNewMessage(messageTextArea.getText());
            messageTextArea.setText("");
            System.out.println("Enter-key pressed!");
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

    /**
     * Wird ein Userhinzugefügt, wird er auf der linken Seite angezeigt, BETA
     * @param username Name des Users, welcher hinzugefügt werden soll.
     */
    private void addNewUserName(String username){
        panel_1.add(new JLabel(username));
    }
}
