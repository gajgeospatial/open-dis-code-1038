
import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.*;

import java.io.*;
import java.net.*;

/**
 * This class is a simple example of a DIS writer. 
 *
 * @author DMcG
 */

 public class WriterExample extends Object
 {
     /** Default socket */
     private DatagramSocket socket = null;

     /** Default destination address */
     private InetAddress defaultAddress = null;

     /** Default destination port */
     private int defaultPort = -1;


     /**
      * Entry point
      */
     public static void main(String programArgs[])
     {
         WriterExample writer = new WriterExample();  // new instance of this class

         try
         {
             // Create a multicast socket
             MulticastSocket sock = new MulticastSocket(6020);                    // Magic number for port
             InetAddress     defaultMcast = InetAddress.getByName("239.252.1.2"); // Site-local, administratively scoped multicast address (RFC2365)
             sock.joinGroup(defaultMcast);
             long timestamp = 0;
             
             // Create and send a bunch of espdus. You can confirm these are being sent via a packet
             // sniffer such as ethereal.

             for(int idx = 0; idx < 100; idx++)
             {
                 EntityStatePdu espdu = new EntityStatePdu();
                 espdu.setTimestamp(timestamp);
                 
                 Vector3Double position = espdu.getEntityLocation();
                 position.setX(idx);
                 position.setY(0.0);
                 position.setZ(0.0);
                
                 // Set varioius things in the espdu here; orientation, etc,
                 // using the espdu API defined by that class.
                 
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 DataOutputStream dos = new DataOutputStream(baos);
                 espdu.marshal(dos);
                 
                 byte[] buffer = baos.toByteArray();
                 
                 DatagramPacket packet = new DatagramPacket(buffer, buffer.length, defaultMcast, 6020);
                 sock.send(packet);

                 // Hang out for a while before writing the next one
                 Thread.sleep(1000);
             }
         }
         catch(Exception e)
         {
             System.out.println(e);
         }

     }

 }
