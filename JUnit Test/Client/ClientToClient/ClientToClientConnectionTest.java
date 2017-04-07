package Client.ClientToClient;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test Klasse für die ClientToClientConnection Klasse
 * Created by meiersila on 06.04.2017.
 */
public class ClientToClientConnectionTest {
    private String host;
    private ClientToClientConnection ctccSErver;
    private ClientToClientConnection ctccclient;

    /**
     * Ein neuer ClientToClientConnection Objekt wird erstellt und der HostServer angegeben.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        host ="172.16.2.156";
        ctccSErver = new ClientToClientConnection(host, true);
        ctccclient = new ClientToClientConnection(host, false);
        ctccSErver.start();
    }

    /**
     * Ist der Test fertig wird in der Konsole die Ausgabe fertig angezeigt.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("fertig");
    }

    /**
     * Eigentliche Testklasse. Überprüft ob der Host korrekt angegeben wurde.
     * Sollte überprüfen ob der Clientsocket gestartet wurde, funktionierte aber nicht wegen des komplexen Aufbau
     */
    @Test
    public void serverTest(){
        Assert.assertNotNull(ctccSErver);
        Assert.assertEquals(true, ctccSErver.getClient().isClosed());
        Assert.assertEquals("172.16.2.156", ctccSErver.getHost());
    }

}