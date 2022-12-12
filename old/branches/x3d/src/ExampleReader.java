
import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.*;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Example DIS reader. <p>
 *
 * @author DMcG
 */
 
 public class ExampleReader extends Object implements Runnable
 {
     /** Some DIS PDUs may actually be bigger--at least 8K--but this is OK for now */
   public static final int MTU_SIZE = 1500;
   
   /** Datagram socket (could be either unicast or multicast) */
   private DatagramSocket socket;
    
   /**
     * Entry point for thread.
     */
    public void run()
    {
        // Alan: moved these outside loop to lower gc
        byte  buffer[] = new byte[MTU_SIZE];
        Pdu pdu = null;
        PduFactory pduFactory = new PduFactory();

        DatagramPacket packet;

        packet = new DatagramPacket(buffer, buffer.length);

      while(true)
      {
        try
        {
          socket.receive(packet);
          
          // We've got a bunch of bytes; we need to transform that into a Java object.
          // The PduFactory object is responsible for that.
          
          pdu = (Pdu)pduFactory.createPdu(packet.getData());

          // Got a PDU; show the type
          if(pdu != null)
          {
             System.out.println("Got PDU of type " + pdu.getClass().getName());
          }
        }
        catch(Exception e)
        {
          System.out.println(e);
          e.printStackTrace();
        }
     } // End while true
    } // end run()
    
    public static void main(String args[])
    {
        ExampleReader reader = null;
        
        try
        {
            MulticastSocket socket = new MulticastSocket(62040);
            socket.joinGroup(InetAddress.getByName("239.1.2.3"));
            
            reader = new ExampleReader();
            reader.setSocket(socket);
            Thread readThread = new Thread(reader);
            readThread.start();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void setSocket(DatagramSocket socket)
    {
        this.socket = socket;
    }
 }