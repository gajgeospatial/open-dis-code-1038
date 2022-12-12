
package edu.nps.moves.darkstar.client;


import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.*;
import edu.nps.moves.disenum.*;

import java.nio.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.sun.sgs.client.ClientChannel;
import com.sun.sgs.client.ClientChannelListener;

/**
 * A listener class that runs on the client and processes DIS messages
 * sent from the server.
 *
 * @author mcgredo
 */
public class DisChannelClientListener implements ClientChannelListener
{
    public static Logger logger = Logger.getLogger(DisChannelClientListener.class.getName());

    private PduFactory factory = new PduFactory();
    
    private Network network;



    public DisChannelClientListener()
    {
        network = null;
    }
    
    /**
     * Constructor; takes a Network object that contains a socket for the local
     * network
     */
    public DisChannelClientListener(Network network)
    {
        this.network = network;
    }

/**
 * Called when the darkstar server instructs us to leave a channel.
 *
 * @param channel Channel we are leaving
 */
public void leftChannel(ClientChannel channel)
{
    logger.log(Level.INFO, "Instructed to leave channel by server, channel=" + channel.getName());
}

/**
 * Receive a PDU from the server over the given channel. If this is a GUI client of
 * some sort, we can decode the PDU and use it to update our view of the screen. If
 * this class is used in some sort of network gateway, we can simply forward it out
 * on the local netowrk interface. Or if we have local content on this host that
 * requires semantic knowledge of the packet, we can decode the packet and
 * extract the information.
 *
 * @param channel The channel we received the message over
 * @param message The message, should be IEEE-1278.1 PDUs
 */
public void receivedMessage(ClientChannel channel, ByteBuffer message)
{

    // there are two likely ways we can use this. Actually, it's usually either
    // on or the other. We may simply be acting as a gateway for darkstar, in which
    // we listen on the local network and forward DIS packets to darkstar, and likewise
    // listen for messages from darkstart (perhaps entities that are persistent
    // on darkstar) and forward those out on the local network.
    
    // Option A: simply send the message (which should already be in IEEE wire
    // format) out on the local network.

    if(network != null)
        network.send(message.array());

    // Option B: decode the message locally and do something or other with it.
    Pdu aPdu = factory.createPdu(message);

    try
    {
        PduType pduType = aPdu.getPduTypeEnum();
        switch(pduType)
        {
            case ENTITY_STATE:
                EntityStatePdu espdu = (EntityStatePdu)aPdu;
                EntityID id = espdu.getEntityID();
                String entityName = "ENTITY(" + id.getSite() + "," + id.getApplication() + "," + id.getEntity() + ")";

                //logger.log(Level.INFO, "++++got espdu for " + entityName);
                break;

            case FIRE:
                break;

            case DETONATION:
                break;

            case COLLISION:
                break;

            default:
        }
    }
    catch(Exception e)
    {
        logger.log(Level.WARNING, "Problem processing PDU " + e);
    }

}

}
