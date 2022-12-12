/*
 * 
 * Class to take DIS ESPDU and send it to multicast group
 */
package edu.nps.moves.xplane;

import java.net.*;
import java.io.*;
import edu.nps.moves.dis.*;

/**
 * @author $DMcG
 * @author $Tariq Rashid
 * 
 */
public class MulticastSender implements Updater {

    /** multicast group we send on */
    public static final String DEFAULT_MULTICAST_GROUP = "239.1.2.3";
    private String MULTICAST_GROUP = DEFAULT_MULTICAST_GROUP;
    private int port;
    MulticastSocket socket;
    InetAddress address;
    /** Port we send on */
    public static final int DEFAULT_PORT = 62040;
    boolean on = false;

    public MulticastSender() {
        port = DEFAULT_PORT;
        setSocket();

    }//end no arguement Multicast sender constructor

    public MulticastSender(String multicastGrp, int port) {
        setMulticastGroup(multicastGrp);
        setPort(port);
        setSocket();

    }//end MulticastSender constructor

    public int getPort() {
        return this.port;
    }//end getPort

    public String getMulticastGroup() {
        return this.MULTICAST_GROUP;
    }//end getMulticastGroup

    private void setSocket() {
        try {
            socket = new MulticastSocket(port);
            address = InetAddress.getByName(MULTICAST_GROUP);
            socket.joinGroup(address);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//end setSocket

    public void setMulticastGroup(String grp) {
        MULTICAST_GROUP = grp;
    }//end setMulticastGrou[

    public void setPort(int newPort) {
        port = newPort;

    }//end setPort

    
    //* Takes open-dis espdu object and sends it out
    public void sendEspdu(EntityStatePdu espdu) {


        // The EID is the unique identifier for objects in the world. This 
        // EID should match up with the ID for the object specified in the 
        // VMRL/x3d world.
        //System.out.println("Outbound Traffic");
        
        
        EntityID eid = espdu.getEntityID();
        eid.setSite(0);
        eid.setApplication(1);
        eid.setEntity(2);

        try {
            // Marshal out the object to a byte array, then send a datagram
            // packet with that data in it.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            espdu.marshal(dos);
            byte[] data = baos.toByteArray();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);

            socket.send(packet);
        } catch (Exception e) {
            System.out.println(e);
        }//end catch

    }//end sendEspdu

     public boolean isOn()
    {
        return on;
    }

    public void setOn(boolean state)
    {
        on = state;
    }
}//end class MulticastSender
