import java.net.*;
import java.io.*;
import java.util.*;

import edu.nps.moves.dis.*;

/**
 * Receives ESPDUs from the network using the library auto-generated from 
 * an XML description.
 *
 * @author DMcG
 */

public class EspduReceiver 
{
    public static void main(String args[])
    {
        MulticastSocket socket;
        DatagramPacket packet;
        InetAddress    address;
        
        try
        {
            socket = new MulticastSocket(EspduSender.PORT);
            address = InetAddress.getByName(EspduSender.MULTICAST_GROUP);
            socket.joinGroup(address);
            
            while(true)
            {
                byte buffer[] = new byte[1500];
                packet = new DatagramPacket(buffer, buffer.length);
                
                socket.receive(packet);
                
                // read the data from a byte array input stream
                ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                DataInputStream dis = new DataInputStream(bais);
                
                EntityStatePdu espdu = new EntityStatePdu();
                
                espdu.unmarshal(dis);
                
                Vector3Double location = espdu.getEntityLocation();
                EntityID eid = espdu.getEntityID();
                System.out.println("Location is " + location.getX() + " " + location.getY() + " " + location.getZ());
                System.out.println("EID is " + eid.getSite() + " " + eid.getApplication() + " " + eid.getEntity());
                
                
            } // end while
        } // End try
        catch(Exception e)
        {
                
            System.out.println(e);
        }
            
        
    } // end main
        
        
} // end class
