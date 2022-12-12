

package edu.nps.moves.darkstar.client;

import java.io.*;
import java.net.PasswordAuthentication;
import java.nio.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.sun.sgs.client.ClientChannel;
import com.sun.sgs.client.ClientChannelListener;
import com.sun.sgs.client.simple.SimpleClientListener;
import com.sun.sgs.client.simple.SimpleClient;

import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.*;


/**
 * Establishes a connection to a darkstar server from the client side, and
 * opens a DIS channel to send ESPDUs.<p>
 *
 * @author mcgredo
 */
public class DarkstarClient implements SimpleClientListener
{
    private static Logger logger = Logger.getLogger(DarkstarClient.class.getName());
    private Random random = new Random();
    private boolean successfullyLoggedIn = false;
    private boolean joinedDisChannel = false;
    private ClientChannel disChannel = null;

    private ClientChannelListener disChannelListener = null;

    /**
     * Returns an object that contains a username and password. The username is psuedo-randomly
     * generated, and the password is a plaintext char array. This is a callback; the system
     * calls this after login() is called on ClientListener. As of right now, there is no
     * password or authentication on the server, and this is just a placeholder.
     * @return
     */
    public PasswordAuthentication getPasswordAuthentication()
    {
        String participant = "guest-" + random.nextInt(); 
        String password = "guest";
        PasswordAuthentication authentication = new PasswordAuthentication(participant, password.toCharArray());
        return authentication;
    }

    /**
     * Called when the login to the server succeedes.
     */
    public void loggedIn()
    {
        //logger.log(Level.INFO, "Successfully logged in, in method loggedIn()");
        successfullyLoggedIn = true;
    }


    /**
     * Called if the login to the server fails.
     *
     * @param reason string explanation of failure
     */
    public void loginFailed(String reason)
    {
        logger.log(Level.INFO, "Login failed: " + reason);
        successfullyLoggedIn = false;
    }

    /**
     * Called when the server disconnects.
     * 
     * @param graceful true if a nice shutdown
     * @param reason description of reason, if any
     */
    public void disconnected(boolean graceful,
                  String reason)
    {
        logger.log(Level.INFO, "Disconnected; graceful " + graceful + " reason:" + reason);
    }

    /**
     * Called if we reconnect
     */
    public void reconnected()
    {
        logger.log(Level.INFO, "reconnected to server");
    }

    /**
     * Called if in process of reconnecting
     */
    public void reconnecting()
    {
        logger.log(Level.INFO, "In the process of reconnecting");
    }

    /**
     * We received a message from the server on the main, direct connection
     * communications channel. This should typically be used for system-level
     * messages; there is a separate class and channel to handle DIS.<p>
     *
     * @param message byte array of message
     */
    public void receivedMessage(ByteBuffer message)
    {
        logger.log(Level.INFO, "Received a message from the server on the application channel");
        /*
        PduFactory factory = new PduFactory();
        Pdu aPdu = factory.createPdu(message);

        if(aPdu instanceof EntityStatePdu)
        {
            EntityStatePdu espdu = (EntityStatePdu)aPdu;
            EntityID id = espdu.getEntityID();
            String eid = "ENTITY(" + id.getSite() + "," + id.getApplication() + "," + id.getEntity() + ")";
            logger.log(Level.INFO, eid);
        }
         * */
    }

    /**
     * Called when the server instructs us that it has joined a channel for us. We get the
     * channel, for which we create a listener.
     * 
     * @param channel the communcations channel
     * @return ClientChannelListener, an object that is notified when messages arrive
     */
    public ClientChannelListener joinedChannel(ClientChannel channel)
    {
        logger.log(Level.INFO, "joined a channel");
        disChannelListener = new DisChannelClientListener();

        if(channel.getName().equals("CHANNEL_DIS"))
        {
            disChannel = channel;
            joinedDisChannel = true;
        }

        return disChannelListener;
    }

    /**
     * Entry point<p>
     * 
     * @param args
     */
    public static void main(String args[])
    {

        DarkstarClient listener = new DarkstarClient();
        SimpleClient client = new SimpleClient(listener);

        Properties connectProperties = new Properties();
        connectProperties.put("host", "localhost");
        connectProperties.put("port", "1139");

        try
        {
            client.login(connectProperties);

            // We need this little wait-dance so that we don't proceed until we
            // have confirmation of login and that we've joined the DIS channel.
            // Otherwise the messages sent to the server fail silently.
            while(listener.joinedDisChannel == false)
            {
               logger.log(Level.INFO, "waiting for DIS channel to be joined....");
               Thread.sleep(1000);
            }
            logger.log(Level.INFO, "successfully joined dis channel");

        }
        catch (Exception e)
        {
            logger.log(Level.INFO, "could not log in");
            e.printStackTrace();
            System.exit(0);
        }


        // Start sending PDUs to the server
        PduFactory factory = new PduFactory();

        int appId = listener.random.nextInt() % 1000;
        
        while(true)
        {

            try
            {
                EntityStatePdu pdu = new EntityStatePdu();
                EntityID id = pdu.getEntityID();
                id.setSite(0);
                id.setApplication(appId);
                id.setEntity(2);

                byte buffer[] = pdu.marshalWithNpsTimestamp();

                //client.send(ByteBuffer.wrap(buffer));
                listener.disChannel.send(ByteBuffer.wrap(buffer));
                //logger.log(Level.INFO, "Sent DIS ESPDU message to server from client on dis channel");
                Thread.sleep(1000);
            }
            catch(Exception eo)
            {
                System.out.println("some problem" + eo);
                System.exit(0);
            }
        }
    }
}
