package edu.nps.moves.dis.xmpp;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smack.filter.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.DefaultPacketExtension;

import edu.nps.moves.dis.*;

import java.io.*;
import java.util.*;
import javax.xml.bind.*;

/**
 * A simple class that sends DIS via XMPP.
 * 
 * @author mcgredo
 */
public class XmppSender
{
    // These should be moved out to a properties file
    public static final String SERVER_NAME="savage.nps.edu";
    public static final String USER_NAME  = "testuser";
    public static final String USER_PASSWORD = "foobar";
    public static final String MUC_SERVER = "conference.savage.nps.edu";
    public static final String MUC_CHATROOM="moves";
    public static final String MUC_USERNAME="studmuffin";
    
    /** Creates a new instance of XmppClient */
    public XmppSender()
    {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            // Creates a new connection, using TLS if possible.
            XMPPConnection connection = new XMPPConnection(SERVER_NAME);
            connection.connect();

            // Login
            connection.login(USER_NAME, USER_PASSWORD);
            
            // Set up code to receive XMPP packets. This is set up as an object
            // that receives callbacks as packets arrive.
            
            // set up a packet filter to listen for only the things we want
            PacketFilter filter = new AndFilter(new PacketTypeFilter(Message.class), 
                                                new FromContainsFilter(MUC_CHATROOM + "@" + MUC_SERVER));
            
            // Next, create a packet listener. We use an anonymous inner class for brevity.
            PacketListener myListener = new PacketListener() {
                
                // The processPacket method is called whenever a message that meets the 
                // criteria in the PacketFilter defined above is received on the 
                // connection.
             public void processPacket(Packet packet) 
             {       
               Message aMessage = (Message)packet;
               
               System.out.println("***Packet contents:" + packet.toXML());
               //System.out.println(((Message)packet).getBody());
               
               // Check to see if we have a "pdu property" attached to
               // this message. This is a Java object serialization format object
               // attached to the message
               Object anObject = aMessage.getProperty("PDU");
               if( (anObject != null) && (anObject instanceof EntityStatePdu))
               {
                   edu.nps.moves.dis.EntityStatePdu espdu = (edu.nps.moves.dis.EntityStatePdu)(aMessage.getProperty("PDU"));
                   {
                       System.out.println("got a PDU object as a property attachment");
                   }
               }
               
               // Check to see if we have an "XML-PDU" property attached. This is a Java
               // String object containing the XML of a PDU.
               anObject = aMessage.getProperty("XML-PDU");
               if( (anObject != null) && (anObject instanceof String))
               {
                 String xmlReceived = (String)anObject;
                 System.out.println("XML received in message is " + xmlReceived);
               
               }
             }
              };
              
         // Register the listener.
         connection.addPacketListener(myListener, filter);
         
            
            // join a multi-user chat room. We supply the connection to
            // our local server and specify the room we want to join, in
            //  a full JID. Then we specify a join, giving the name that we
            // want to use in the chatroom. Note that this might not be the
            // same as our user login name.
            MultiUserChat muc = new MultiUserChat(connection, MUC_CHATROOM + "@" + MUC_SERVER);
            muc.join(MUC_USERNAME);
            
            // create a series of DIS packets, and then send them on XMPP
            for(int idx = 0; idx < 10; idx++)
            {
                 EntityStatePdu espdu = new EntityStatePdu();
                 espdu.setTimestamp(idx);
                 
                 Vector3Double position = espdu.getEntityLocation();
                 position.setX(idx);
                 position.setY(0.0);
                 position.setZ(0.0);
                 
                 EntityID id = espdu.getEntityID();
                 id.setSite(0);
                 id.setApplication(1);
                 id.setEntity(2);
                 
                 // At this point we can take a couple courses of action to send
                 // the object across XMPP. Since ESPDUs implement the Serializable
                 // interface, we can attach the object as a property. This is the
                 // first technique shown.
                 Message message = muc.createMessage();
                 message.setBody("This has an entity state PDU as an attachement in Java object serialization format");
                 message.setProperty("PDU", espdu);
                 muc.sendMessage(message);

                 // We can also marshall the ESPDU to XML and send that as an attachment, again
                 // in string format. This is still limited to java-only, since it is a 
                 // java string object.
                 List<Pdu> pduList = new ArrayList<Pdu>();
                 pduList.add(espdu);
                 PduContainer container = new PduContainer();
                 container.setPdus(pduList);
                 
                 JAXBContext context = JAXBContext.newInstance(PduContainer.class, EntityStatePdu.class);
                 Marshaller marshaller = context.createMarshaller();
                 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 marshaller.marshal(container, baos); 
                 String xml = new String(baos.toByteArray());
                 
                 message = muc.createMessage();
                 message.setBody("This has an entity state PDU as an attachement as a Java XML string");
                 message.setProperty("XML-PDU", xml);
                 muc.sendMessage(message);
                
                 Thread.sleep(1000);
            }
            
            // The "offical" way to send XML extensions to a message is via a
            // PacketExtension. PacketExtensions are additions to the XML stanza;
            // for example, <message to="blah@foo.com" body="this is the message
            // body"> <foo xmlns="http://nps.edu"> <color>blue</color></foo></message>
            // Note that the "foo" element is not part of the standard message
            // format. 
            // 
            // This means we need to write a parser to handle this new information.
            // There is a default parser so long as the XML is very simple--no
            // attributes, only looking for text nodes in elements. That is the 
            // DefaultPacketExtension.
            
            Message chatRoomMessage = muc.createMessage();
            DefaultPacketExtension defaultExtension = new DefaultPacketExtension("Foo", "https://www.nps.edu/Foo");
            defaultExtension.setValue("Address", "1 Dyer Circle");
            defaultExtension.setValue("PhoneNumber", "831-555-1212");
            chatRoomMessage.addExtension(defaultExtension);
            chatRoomMessage.setBody("this is a message with a DefaultPacketExtension");
            muc.sendMessage(chatRoomMessage);
            
            /* The XML looks like this:
             * <message id="p6ev57" to="mcgredo@savage.nps.edu" type="groupchat"
             * from="moves@conference.savage.nps.edu/studmuffin" <body>this is a
             * message with a defaultPacketExtension</body> <Foo xmlns-"https://www.nps.edu/Foo">
             * <PhoneNumber>831-555-1212</PhoneNumber><Address>1 Dyer Circle</Address>
             * </Foo></message>
             */
            
            // What if you have a piece of XML that you want to attach? You have to write
            // your own subclass of PacketExtension, and a PacketExtensionProvider.
            // PacketExtensionProvider has a parseExtension() method.
            
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
