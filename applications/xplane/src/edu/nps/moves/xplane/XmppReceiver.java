/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Logs in to 
 * 
 */
package edu.nps.moves.xplane;

import org.jivesoftware.smack.XMPPConnection;
//import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
//import org.jivesoftware.smack.filter.*;
import org.jivesoftware.smack.packet.Message;
//import org.jivesoftware.smack.packet.Packet;
//import org.jivesoftware.smack.packet.DefaultPacketExtension;
//import org.w3c.dom.*;

import edu.nps.moves.dis.*;
//import edu.nps.moves.disenum.*;

/**
 *
 * @author $Tariq Rashid
 */
public class XmppReceiver {

    public static void main(String args[]) {

        String chatServer = "savage.nps.edu";
        String loginName = "guest";
        String passWord = "guest";
        String multiUserConferenceRoomName = "dispduexchange@conference.savage.nps.edu";
  

        XMPPConnection connection;
        MultiUserChat muc;
        
        Object o;

        try {
            connection = new XMPPConnection(chatServer);
            connection.connect();

            // Login
            connection.login(loginName, passWord, "gateway");


            // join a multi-user chat room. We supply the connection to
            // our local server and specify the room we want to join, in
            //  a full JID. Then we specify a join, giving the name that we
            // want to use in the chatroom. Note that this might not be the
            // same as our user login name.
            muc = new MultiUserChat(connection, multiUserConferenceRoomName);
            muc.join("pduReceiver");
           

            Message chatRoomMessage;
            while (true) {

                chatRoomMessage = muc.nextMessage();
                try
                {
                //This only works using Smack on both ends
                o = chatRoomMessage.getProperty("pdu");
                System.out.println(o.getClass().toString());

           
                }catch(java.lang.NullPointerException npe){
                    System.out.println(npe.toString());
                }
                
                
            }//End while
        //Thread.sleep(500);


        } // End try
        catch (Exception e) {

            System.out.println(e);
        }


    } // end main
}
