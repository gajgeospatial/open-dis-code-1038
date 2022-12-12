/*
 * The UDP Listener is a runnable that will open a socket based upon the passed
 * in address and port.  Whenever it recieves an X-Plane UDP It calls the
 * interpreter and turns the X-plane UDP byte stream into a data object
 * 
 * This is the core of the xplane Listener
 * 
 * Is listens for UPDs, calls interpreter methods, and sends out DIS as needed
 * 
 */
package edu.nps.moves.xplane;

import java.net.*;
import java.util.Map.*;
import java.io.*;


import edu.nps.moves.dis.EntityID;
import edu.nps.moves.dis.Vector3Double;
import edu.nps.moves.dis.Vector3Float;

/**
 *
 * @author $Tariq Rashid
 */
public class XPlaneUDPListener implements Runnable {

    public final boolean DEBUG = true;
    private static final String DEFAULT_ENTITY_NAME = "X-Plane";
    private static final String DEFAULT_ADDRESS = "239.3.2.2";
    private static final int DEFAULT_PORT = 49000;
    
    
    Vector3Double origin = new Vector3Double();
    Vector3Float stopped = new Vector3Float();

    public static enum CONNECTION_TYPE{LOCAL_HOST,MULTI_CAST,UNI_CAST};
    public final static ConnectionManager.CONNECTION_TYPE DEFAULT_CONNECTION_TYPE
            = ConnectionManager.CONNECTION_TYPE.MULTI_CAST;
        
    private int dataGroups;
    
    //This number needs to match the number of output parameters you have set in Xplane
    //Later on I will make the program automatically set this
    public static final int NUM_DATA_GROUPS_TO_LISTEN_FOR = 5;    

    /** Authoritative data for the xplane entity, updated from the xplane application */
    private XPlaneActor xPlaneActor;
    
    /** A "ghost", intended to represent an entity on a remote host. It handles
     * dead reckoning, and when the ghost deviates by more than a given amount,
     * or by more than a certain amount of time since the last update, it fires
     * a method to send a DIS pdu to the network
     */
    private XPlaneActor ghost;

    
    private String message;
    private EntityID ID;
    private String entityName;
    private ConnectionManager connectionManager;   
    private boolean entityPositionOutputIsFixed = false;
 
    public java.lang.Thread runUDPListener;

    
    /**
     * 
     */
    public XPlaneUDPListener() {

        this(DEFAULT_ENTITY_NAME, DEFAULT_ADDRESS, DEFAULT_PORT, DEFAULT_CONNECTION_TYPE);
      
    }//End constructor
    
    /**
     * 
     * @param name
     * @param newMulticastAddress
     * @param newPort
     * @param type
     */
    public XPlaneUDPListener(String name, String newMulticastAddress, int newPort,ConnectionManager.CONNECTION_TYPE type) {

        entityName = name;
       
        connectionManager = new ConnectionManager();
        connectionManager.setAddress(newMulticastAddress);
        connectionManager.setPort(newPort);
        connectionManager.setListeningConnectionType(type);
        ID = new EntityID();
        
          //Make an actor
        xPlaneActor = new XPlaneActor(XPlaneActor.ActorType.ACTOR);

        //Make a ghost
        ghost = new XPlaneActor(XPlaneActor.ActorType.GHOST);
      
        
    }//End UDP Listener constructor

    public void setEntityID(int id) {
        ID.setEntity(id);
        
        
    }//End setEntityID

    public void setEntityName(String name) {
        entityName = name;
    }//End setEntityName

  
    public String getEntityName() {

        return this.entityName;
    }//

  
    public EntityID getID() {
        return this.ID;
    }

    public ConnectionManager getConnectionManager(){
        return this.connectionManager;
    }

    
    public XPlaneActor getXPlaneActor(){   
        return this.xPlaneActor;
    }
 
    public void setEntityPositionOutputFixed(boolean set){
        
        entityPositionOutputIsFixed = set;
        
    }
    
    
 
    @Override
    /**
     * @return String
     */
    public String toString() {
         
        return (ID.getEntity() + "   " + entityName + "   " 
                + connectionManager.getAddress() + "   " +connectionManager.getPort() 
                + "   Listener Status: " +((connectionManager.isUDPCaptureActive()) ? "ACTIVE" : "OFF")
                + "   Output: Multicast: " + ((connectionManager.getMulticastStatus()) ? "ON" : "OFF") 
                + "   XMPP: " + ((connectionManager.getXmppStatus()) ? "ON" : "OFF") 
                + "   KML: " + ((connectionManager.getKmlStatus()) ? "ON" : "OFF")
                 );
    }

    /**
     * @param XPlaneUDPListener
     * @return boolean
     */
    public boolean isEqual(XPlaneUDPListener listener) {

        if (this.entityName.equals(listener.entityName) 
                && this.connectionManager.getAddress().equals(listener.getConnectionManager().getAddress()) 
                && this.getConnectionManager().getPort() == listener.connectionManager.getPort() 
                && this.getConnectionManager().getSendersAddress().equals(listener.getConnectionManager().getSendersAddress()) ){
            return true;
        }else {
            return false;
        }
    }//end is equal
    
    /**
     * @param DatagramSocket
     * @return DatagramPacket
     * @throws IOExceptions
     * 
     * readPacket does the work of listening to the socket for the next multicast socket
     */
    public DatagramPacket readPacket() throws IOException {

        DatagramPacket packet;

        dataGroups = NUM_DATA_GROUPS_TO_LISTEN_FOR;
        int udpLength = XPlanePacketParser.HEADER_LENGTH + dataGroups *XPlanePacketParser.DATA_GROUP_ARRAY_LENGTH;

        byte[] buf = new byte[udpLength];
        packet = new DatagramPacket(buf, buf.length);
       
       
        try {
             connectionManager.getDatagramSocket().receive(packet);

        } catch (SocketException se) {
        }


        return packet;

    }//End readPacket


    
      /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//       
//        XPlaneUDPListener udpl = new XPlaneUDPListener();
//        
//        
//        udpl.getConnectionManager().setAddress("224.0.0.1");
//        udpl.getConnectionManager().setPort(49000);
//        
//          //This is how you control the while loop inside the listener
//
//      
//        //Instantiate a Thread
//        udpl.runUDPListener = new java.lang.Thread(udpl);
//
//        //Start the thread
//        udpl.runUDPListener.start();
//        
//        //udpl.run();
//        
//    }
    

    
    public String getMessage(){
        return this.message;
    }
  
    /**run
     * @param 
     * @return 
     */
    public void run() {
         origin.setX(0.0);
         origin.setY(0.0);
         origin.setZ(0.0);
         stopped.setX(0.0f);
         stopped.setY(0.0f);
         stopped.setZ(0.0f);
        
        //if (DEBUG) {
            long timer;
            long startTime;
            startTime = System.currentTimeMillis();
        //}
       
        //Make a local packet
        DatagramPacket myPacket;
        XPlanePacketParser  xPlaneUdpParser = new XPlanePacketParser();
        //EntityStatePdu espdu;
        
        //Make an actor
        xPlaneActor = new XPlaneActor(XPlaneActor.ActorType.ACTOR);

        //Make a ghost
        ghost = new XPlaneActor(XPlaneActor.ActorType.GHOST);
        
        //Make a simulator to record packets
         //XPlaneOutputSimulator outputSimulator = new XPlaneOutputSimulator();

        //Make a connection
        try {
            connectionManager.makeSocketConnection();
            //connectionManager.setUPDActive(true);
           if (DEBUG) System.out.println("Connection Made " + connectionManager.getAddress());
            
           
        } catch (IOException ioe) {
            System.out.println("Connection Failed Address" 
                    + connectionManager.getAddress() + " Port" + connectionManager.getPort());
        }
        
   
     
        //Start the a listening loop
        while (connectionManager.isUDPCaptureActive())
        {
            
             //if (this.runUDPListener.isInterrupted()) this.connectionManager.setUPDActive(false);
             
            if (this.runUDPListener.isInterrupted()){
       
                break;
            }
            
              if(DEBUG)
              {
                  
                  //System.out.println("KML is " + this.connectionManager.getKmlStatus());
                  
                   //System.out.println("Multicast status is " + this.connectionManager.getMulticastStatus());
                  
                  //System.out.println( "In the loop Listener status is " + this.connectionManager.isUDPCaptureActive());
                  
                  timer = System.currentTimeMillis() - startTime;
                  
                  //System.out.println("Elapsed Time is "  + timer/1000);
                  
                  
              }
            
              //   if (DEBUG)  System.out.println(this.connectionManager.isUDPCaptureActive());
           
         try {
               myPacket = readPacket();
         
             //Write the packet to a file
             //outputSimulator.writeToOutFile(myPacket,"packets.dat");
           
            //Parse the packet.  The espdu is updated inside of xPlaneUdpParser
             xPlaneUdpParser.parseUDP(myPacket);
            
             //Set the ID inside the espdu to this listener ID
             xPlaneUdpParser.getEspu().setEntityID(ID);
             
            
             
              if (this.entityPositionOutputIsFixed){
                xPlaneUdpParser.getEspu().setEntityLocation(origin);
                xPlaneUdpParser.getEspu().setEntityLinearVelocity(stopped);
             }
             
             
            //get the espdu and update the actor
             xPlaneActor.updateActor(xPlaneUdpParser.getEspu());
             
             //if(DEBUG) System.out.println(xPlaneUdpParser.toString());
              // message = (xPlaneUdpParser.toString());
           
              //If the FixedPosition is enabled then set the epsdu location to 0,0,0
             //And set the velocity to 0
            
            
             //Dead reckon the ghost each time a packet is received
             //Note that the PDU is only passed in to calculate the timesteps
             //The ghosts dead reckoning parameters are not updated by 
             //  ghost.deadReckon

               ghost.deadReckon(xPlaneUdpParser.getEspu());
       
            /**Compare the espdu state to the ghost state
            *If deadReckoningIsGood compares back false then transmit then
             * Only update the ghost when XplaneDisApp forwards the packet
            */
            if (!ghost.deadReckoningIsGood(xPlaneUdpParser.getEspu())){
                ghost.updateActor(xPlaneUdpParser.getEspu());
                 
               connectionManager.sendEspdu(xPlaneUdpParser.getEspu());
                   
              //if (DEBUG) System.out.println("ESPDU Transmitted");
            }
            
          } catch (IOException ioe) {
            if (DEBUG){
                System.out.println("Failed to Read Packet");
                 System.out.println("Exception " + ioe.toString());
            }
            
        }

        }//End while


    }//End run
    

    

}//End class XPlaneUDPListener
