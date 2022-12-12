
package edu.nps.moves.darkstar.client;

import java.net.*;
import java.nio.*;

/**
 *
 * @author mcgredo
 */
public class Network implements Runnable
{
    DatagramSocket socket;
    int port;
    InetAddress multicastAddress;

    public void setSocket(DatagramSocket socket, int port, InetAddress multicastAddress)
    {
        this.socket = socket;
        this.port = port;
        this.multicastAddress = multicastAddress;
    }

    public void run()
    {
        while(true)
        {
            byte buffer[] = new byte[1500];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try
            {
                socket.receive(packet);
                byte ieeeData[] = packet.getData();


            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        
    }

    public void send(byte[] buffer)
    {
        try
        {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastAddress, port);
            socket.send(packet);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


}
