
package edu.nps.moves.net;

import java.net.*;
import java.io.*;
import java.util.*;

import edu.nps.moves.dismobile.*;
import edu.nps.moves.disutil.*;

/**
 * A broadcast NetConnection, which reads datagram/UDP packets sent to it, and sends
 * datagram/UDP packets to the broadcast address.<p>
 * 
 * This implements the Runnable interface, so that it reads data from the wire.
 * The Runnable interface is implemented in the concrete subclasses, since potentially
 * not every concrete implementation of NetConnection needs it.<p>
 * 
 * @author mcgredo
 */
public class NetConnectionBroadcast implements NetConnection, Runnable
{
    /** Time, in ms, before the socket times out on a read. In normal use this
     * simply gives us a chance to check if we should exit the read loop.
     */
    public static final int SOCKET_TIMEOUT_PERIOD = 5000;
    
    /** Thread that reads packets from the net */
    Thread         readThread;
    
    /** unicast socket */
    DatagramSocket socket;
    
    /** address to send to */
    InetAddress    destinationAddress;
    
    List<InetAddress> destinationAddresses;
    
    /** Port to read from */
    int            port;
    
    /** port to send to */
    int            destinationPort;
    
    /** User-friendly name */
    String         name;
    
    boolean sendToAllInterfaces = false;
    
    /** Boolean flag to exit read loop */
    boolean        continueRunning = true;
    
    /** Send received PDUs to this for distribution to interested parties */
    PduReceiver pduReceiver;
    
    /**
     * Sending to bcast is tricker than it sounds. There may be multiple interfaces on the
     * host, each with zero or more IPs--for example, a host with ethernet, wireless, and
     * a couple VM host network interfaces, and a couple IPs for each interface, and a IPv6
     * address or three. If we have
     * a specific bcast address to send to, we use it; if not, we send to EVERY interface
     * IP. This mirrors the INADDR_ANY behavior of some other UDP situations, but may
     * not be what you want. If that isn't what you want, specify a bcast address. 
     * Also, there are other wrinkles, such as sub-interfaces, not
     * dealt with here.<p>
     * 
     * In short, be careful.
     * 
     * @param description 
     */
    public NetConnectionBroadcast(NetConnectionDescription description)
    {
        try
        {
            //destinationAddress = InetAddress.getByName(description.connectionProperties.getProperty("destinationAddress"));
            port = Integer.parseInt(description.connectionProperties.getProperty("port"));
            destinationPort = Integer.parseInt(description.connectionProperties.getProperty("destinationPort"));
            String bcastAddress = description.connectionProperties.getProperty("destinationAddress");
            destinationAddresses = new ArrayList<InetAddress>();
            
            if(bcastAddress == null)
            {
                sendToAllInterfaces = true;
                this.addAllBcastAddresses(destinationAddresses);
            }
            else
            {
                InetAddress addr = InetAddress.getByName(bcastAddress);
                destinationAddresses.add(addr);
            }
           
            socket = new DatagramSocket(port);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
        
    }
    
    /**
     * Loop through all the interfaces (en0, eth1, lo0, etc) and get the bcast
     * address from each. If it's IPV6, there's no bcast address. Likewise for
     * non-IPv4 interfaces, such as a fw0 interface with no IP assigned. The
     * addresses are added to the destAddress parameter.
     * 
     * @param destAddresses 
     */
    private void addAllBcastAddresses(List destAddresses)
    {
        try
        {
          Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); 
          while(interfaces.hasMoreElements())
          {
              NetworkInterface anInterface = interfaces.nextElement();
              
              // Skip if the network interface isn't up
              if(!anInterface.isUp())
                  continue;
              
              // Loop through all the interface addresses on this interface
              List<InterfaceAddress> interfaceAddresses = anInterface.getInterfaceAddresses();
              for(int idx = 0; idx < interfaceAddresses.size(); idx++)
              {
                  InterfaceAddress ifAddr = interfaceAddresses.get(idx);
                  InetAddress aBcastAddress = ifAddr.getBroadcast();
                  
                  // Some interfaces do not have bcast addresses--for example, IPv6. In that
                  // case, null is returned, and we can't have that.
                  if(aBcastAddress != null)
                  {
                    System.out.println("Broadcast address: " + aBcastAddress);
                    destAddresses.add(aBcastAddress); 
                  }
              }
          }

        }
        catch(Exception e)
        {
            System.out.println("Interface not found");
        }
        
    }
    
    /** It's dangerous to start a thread from inside a constructor, so we have to expose a start()
     * method as well.
     */
    public void start()
    {
        readThread = new Thread(this);
        readThread.start();
    }
    
    public void stopReadThread()
    {
        continueRunning = false;
    }
    
    public NetConnectionDescription.ConnectionType getConnectionType()
    {
        return NetConnectionDescription.ConnectionType.UDP_BROADCAST;
    }
    @Override
    /** Reads packets from the wire, turns them into DIS PDUs */
    public void run()
    {
        PduFactory pduFactory = new PduFactory();
        
        try
        {
            socket.setSoTimeout(SOCKET_TIMEOUT_PERIOD);
            while(continueRunning)
            {
                byte buffer[] = new byte[1500];
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
                
                socket.receive(datagram);
                
                pduReceiver.receivePdu(datagram.getData());
                
            }
        }
        catch(SocketTimeoutException ste)
        {
            // Normal, ignore; this just gives us a chance to text the while loop exit criteria
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
    @Override
    public void sendData(byte[] data)
    {
        try
        {
            for(int idx = 0; idx < destinationAddresses.size(); idx++)
            {
                DatagramPacket packet = new DatagramPacket(data, data.length, destinationAddresses.get(idx), destinationPort);
                socket.send(packet);
            }
        }
        catch(Exception e)
        {
            System.out.println("Unable to send PDU");
            e.printStackTrace();
            System.out.println(e);
        }
        
    }
    
    @Override
    public void setPduReceiver(PduReceiver pduReceiver)
    {
        this.pduReceiver = pduReceiver;
    }
    
    @Override
    public void finalize()
    {
        System.out.println("In finalize method for NetConnectionBroadcast");
        try
        {
            super.finalize();
            if(socket != null && socket.isBound())
            {
                socket.close();
            }
        }
        catch(Throwable t)
        {
            System.out.println(t);
        }
    }
    
}
