/*
 * Sends out position updates on an XMPP (chat/IM) group
 */
package edu.nps.moves.xplane;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smack.filter.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.DefaultPacketExtension;
import org.jivesoftware.smackx.packet.Bytestream;
import org.w3c.dom.*;

import edu.nps.moves.dis.*;
import edu.nps.moves.disenum.*;

/**
 *
 * @author $Tariq Rashid
 */
public class XmppSender implements Updater {

    private XMPPConnection connection;
    private MultiUserChat muc;
    PacketFilter filter;
    Object o;
    String chatServer = "savage.nps.edu";
    String loginName = "guest";
    String passWord = "guest";
    String multiUserConferenceRoomName = "dispduexchange@conference.savage.nps.edu";
    boolean on = false;

    /** Creates a new instance of XmppSender */
    public XmppSender() {
    }

    public void establishConnection() {
        try {
            // Creates a new connection, using TLS if possible.
            connection = new XMPPConnection(chatServer);
            connection.connect();

            // Login
            connection.login(loginName, passWord);


            // join a multi-user chat room. We supply the connection to
            // our local server and specify the room we want to join, in
            //  a full JID. Then we specify a join, giving the name that we
            // want to use in the chatroom. Note that this might not be the
            // same as our user login name.
            muc = new MultiUserChat(connection, multiUserConferenceRoomName);
            muc.join("xPlane");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void sendEspdu(EntityStatePdu espdu) {
        
        Bytestream bs = new Bytestream();
        
        
        bs.setMode(Bytestream.Mode.udp);
        
        bs.addStreamHost(multiUserConferenceRoomName,chatServer);

        
        try {
            Message chatRoomMessage = muc.createMessage();
            chatRoomMessage.setBody(espdu.toString());
            
            //This only works is Smack is on the retrieving end
            chatRoomMessage.setProperty("pdu", espdu);
             
            muc.sendMessage(chatRoomMessage);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isOn()
    {
        return on;
    }

    public void setOn(boolean state)
    {
        on = state;
    }
}




    
    

