/*
 *  The ConnectionManager class is the repository for all the information
 * about the network communications between the XplaneDis App and Xplane
 * 
 * Its purpose is to support modular construction.  A connection manager can
 * be added to any listener
 * 
 * It does not actually read data but provides a connection to external methods
 * that do.
 * 
 * Connection manager maintains the profile of both the recieving connection from
 * Xplane and which sending capabilities are enabled
 * 
 */

package edu.nps.moves.xplane;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.*;
import java.io.*;
import edu.nps.moves.dis.*;

/**
 *
 * @author $Tariq Rashid
 * @version 1.0
 */
public class ConnectionManager {
     
    public static final boolean DEBUG = true;
    public static final String DEFAULT_ENTITY_NAME = "X-Plane";
    public static final String DEFAULT_MULTICAST_ADDRESS = "239.3.2.2";
    public static final int DEFAULT_PORT = 49000;
    public static final int DEFAULT_TIMEOUT = 500;
    
    public static enum CONNECTION_TYPE{LOCAL_HOST,MULTI_CAST,UNI_CAST};
    public final static CONNECTION_TYPE DEFAULT_CONNECTION_TYPE = CONNECTION_TYPE.MULTI_CAST;
    private CONNECTION_TYPE connectionType;
    
    private String multicastAddress; //Set your output address in X-plane to this
    private String unicastAddress;
    
    private String sendersAddress; 
    private MulticastSender multicastSender;
    private XmppSender xmppSender;
    private KMLSender kmlWriter;
    private List<Updater> updaters = new ArrayList<Updater>();
    
    private boolean isKmlOn = false;
    private boolean isXmppOn = false;
    private boolean isMulticastOn = false;    
    private boolean udpCaptureActive = false;

    private int port;  //This is passed in
    private int snapPort = 49001; //This is also passed in, this where the vehn and snap packets are sent
    
    DatagramSocket dSocket;
    DatagramSocket dSnapSocket;
    
    public ConnectionManager(){
        kmlWriter = new KMLSender();
        multicastSender = new MulticastSender();
        xmppSender = new XmppSender();
        
        updaters.add(kmlWriter);
        updaters.add(multicastSender);
        updaters.add(xmppSender);
    }
    
    public void setKml(boolean kmlOn) {
        kmlWriter.setOn(kmlOn);
    }//end setKML

    public void setXmpp(boolean xmppOn) {
        xmppSender.setOn(xmppOn); 
    }//end set Xmpp

    public void setMulticast(boolean multicastOn) {
        multicastSender.setOn(multicastOn); 
    }
    
    public void setSnapPort(int newPort) {
        snapPort = newPort;
    }//End setSnapPort

    public void setAddress(String address) {
        multicastAddress = address;
    }//End setAddress

    
    public void setListeningConnectionType(ConnectionManager.CONNECTION_TYPE type){
        connectionType = type;
    }
    
    public CONNECTION_TYPE getConnectionType(){
        
        return this.connectionType;
    }
    
    
    public void setUPDActive(boolean newStatus) {
        udpCaptureActive = newStatus;
    }

    public void setPort(int newPort) {
        port = newPort;
    }//end setPort
    
    
     public String getAddress() {

        return this.multicastAddress;
    }//end getAddress
    
        public int getPort() {
        return this.port;
    }

    public int getSnapPort() {
        return this.snapPort;
    }
    
    
    public String getSendersAddress(){
        return this.sendersAddress;
    }
    
    
    
    public DatagramSocket getDatagramSocket(){
        return this.dSocket;      
    }
    
    
    public DatagramSocket getDatagramSnapSocket(){       
        return this.dSnapSocket;
    }
    
    /**
     * 
     */
    public void makeSocketConnection() throws IOException {

        switch (connectionType) {
            case MULTI_CAST:
                 {
                     System.out.println("Starting Packet Listener at " + this.multicastAddress
                + " " + this.port  + " Connection " + connectionType.toString());
                     
                    dSocket = new MulticastSocket(this.getPort());
                    //dSocket.setSoTimeout(DEFAULT_TIMEOUT);
                    
                    ((MulticastSocket)dSocket).joinGroup(InetAddress.getByName(this.multicastAddress));

                    //This is to listen for Xplane SNAP and VEHN packets
                    dSnapSocket = new MulticastSocket(snapPort);
                    dSnapSocket.setSoTimeout(DEFAULT_TIMEOUT);
                    ((MulticastSocket)dSnapSocket).joinGroup(InetAddress.getByName(this.multicastAddress));
                    
                    
                }
                break;


            case UNI_CAST:
                 {
                
                     dSocket = new DatagramSocket(this.port,InetAddress.getByName(this.unicastAddress));
                     dSocket.connect(InetAddress.getByName(this.unicastAddress), this.port);
                     
                    System.out.println("unicast socket made");
                }
                break;



            case LOCAL_HOST:
                 {
                      System.out.println("local host");
                      
                      dSocket = new DatagramSocket(this.port);
                }
                break;
            default: {
            }

        }
    }//End makeSocketConnection
    
    
    
    /**
     * If passed a String address it will attempt to determine what type of connection this
     * corresponds to, returns null object is it fails to resolve
     * 
     * @param  address
     * @return
     */
    public CONNECTION_TYPE resolveConnectionType(String address){
        
        java.net.InetAddress addr;
        
        
        try{
            addr = java.net.InetAddress.getByName(address);
        }catch(java.net.UnknownHostException ukh){
            if (DEBUG) System.out.println("Does not appear to be a valid address");
            return null;
        }
        
        //Read the first three digits
       if (addr.isLoopbackAddress()) return CONNECTION_TYPE.LOCAL_HOST;
       if (addr.isMulticastAddress()) return CONNECTION_TYPE.MULTI_CAST;
       return CONNECTION_TYPE.UNI_CAST;
    }

    /**
     * Sends an espdu out on any of three output mechanisms: IEEE multicast,
     * XMPP or KML.
     *
     * @param espdu The espdu containing data to be sent to the channels
     */
   public void sendEspdu(EntityStatePdu espdu)
   {
       if (DEBUG) System.out.println("Call to sendEspdu made");
       
       for(int idx = 0; idx < updaters.size(); idx++)
       {
           Updater anUpdater = updaters.get(idx);
           if(anUpdater.isOn())
           {
               anUpdater.sendEspdu(espdu);
           }
       }
       
   }
     
    public boolean isUDPCaptureActive() {
        return this.udpCaptureActive;
    }
    
    public boolean getMulticastStatus() {
        return this.multicastSender.isOn();
    }
    
      public boolean getXmppStatus() {
          return this.xmppSender.isOn();
    }

    public boolean getKmlStatus() {
        return this.kmlWriter.isOn();
    }

    public MulticastSender getMulticastSender() {
        return this.multicastSender;
    }
    
    public KMLSender getKMLWriter(){
        return this.kmlWriter;
    }
    
    public XmppSender getXmppSender(){
        return this.xmppSender;
    }
    
    
    

}
