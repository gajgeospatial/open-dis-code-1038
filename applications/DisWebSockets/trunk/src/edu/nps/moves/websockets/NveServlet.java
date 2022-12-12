
package edu.nps.moves.websockets;

import java.io.IOException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketFactory;

import edu.nps.moves.net.*;
import edu.nps.moves.dismobile.*;
import edu.nps.moves.disutil.*;

import org.codehaus.jackson.map.ObjectMapper;
 
/**
 * A "networked virtual environment" servlet. This runs within a Jetty container
 * and handles three types of connections. First, connections from web clients
 * using the nve websocket protocol, which is a bidirectional DIS in JSON format.
 * Second, native DIS on the web server's local network interface, which reads
 * native DIS and sends it out to web clients and bridges. Third,  websockets
 * using the "nveb" protocol, native binary DIS.
 * <p>
 * 
 * A packet received from a web client in JSON format is sent out to the other
 * web clients in JSON format, and also forwarded out on the local native interface
 * 
 * 
 * @author mcgredo
 */
public class NveServlet extends HttpServlet 
{
    /** String tag for the networked virtual environment (NVE) websocket protocol intended to handle JSON-ized traffic */
    public static final String NVE_PROTOCOL = "nve";
    
    /** string for binary protocol */
    public static final String NVE_BINARY_PROTOCOL = "nveb";
    
    /** Timeout for a websocket connection with no traffic, in ms */
    public static final int WEBSOCKET_TIMEOUT = 300000; 
    public static final int WEBSOCKET_BUFFER_SIZE = 8 * 1024; 
    
    
  /** The web socket factory holds shared code necessary to create a web socket */
  private WebSocketFactory websocketFactory;
  
  /** A Set container that contains the various  clients that have connected. Thread safe.
   */
  static final Set<NveSocket> connections = new CopyOnWriteArraySet<NveSocket>();
  

  /** EntityIDs and what connection they're associated with */
  static final ConcurrentHashMap<EntityID, NveSocket> entitiesAndConnections = new ConcurrentHashMap<EntityID, NveSocket>();
 
  /** Initialize the servlet. This is called at servlet startup.  Note that this
   * will not run until the first web client connects. 
   */
  @Override
  public void init() throws ServletException
  {
    // Create and configure WS factory. This uses an anonymous class, WebSocketFactory.Acceptor(),
    // that checks to make sure the origin is acceptable (by default all are) and that
    // the protocol being requested, "nve", is acceptable. The client can specify that
    // a particular protocol be used, and in this case we're passing back simple JSON
    // objects related to a made-up networked virtual environment protocol.
      
    websocketFactory = new WebSocketFactory(new WebSocketFactory.Acceptor()
    {
      /** Can check that the origin (ip/host of the client) is acceptable */
      public boolean checkOrigin(HttpServletRequest request, String origin)
      {
        // Allow all origins
        return true;
      }

      /** The client can specify a "protocol", ie what it expects from the server.
       * In this case the client specifies "nve", and our code will return an 
       * object that speaks JSON and passes back simple JSON objects with the
       * location.
       */
      public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol)
      {
         System.out.println("Protocol requested by client is " + protocol);
         if (NVE_PROTOCOL.equals(protocol))
         {
           // Important! This is where the specific type of websocket class is created.
           // this creates an NVE socket, which can send and receive DIS packets in JSON
           // format.
             System.out.println("Creating a JSON format socket for web browsers");
           return new NveDisJsonSocket(NveServlet.this);
         }
         
         /** This protocol handles binary native DIS format */
         if (NVE_BINARY_PROTOCOL.equals(protocol))
         {
             System.out.println("Creating a binary DIS format socket for bridging");
             return new NveDisBinarySocket(NveServlet.this);
         }
         
         // Create other message handlers here for any sub-protocols. This tends
         // to be not a good idea in practice; you need to tie several connections from
         // the same web page together
         
         return null;
      }
    });
    
    // Set some default values
    websocketFactory.setBufferSize(WEBSOCKET_BUFFER_SIZE);
    websocketFactory.setMaxIdleTime(WEBSOCKET_TIMEOUT);

   /* 
    NetConnectionDescription connectDescription = new NetConnectionDescription(netConnectionProperties);
    NetConnectionFactory connectionFactory = new NetConnectionFactory();
    nativeConnection = (NetConnectionMulticast)connectionFactory.netConnectionForDescription(connectDescription);
    
    nativeConnection.setPduReceiver(this);
    * */

  }
 
  /** A  client (either a bridge or a web client connection) has connected. Add it to our list */
  public void addNveConnection(NveSocket newConnection)
  {
      connections.add(newConnection);
  }
  
  /** A web client has left, perhaps by closing the web browser window or timeout.
   * Remove it from our list
   */
  public void removeNveConnection(NveSocket aNVESocket)
  {
    connections.remove(aNVESocket);  
    this.removeEntitiesForNveSocket(aNVESocket);
  }
  
  
  /**
   * Got a PDU from a bridge/local connection in binary format. Send it out to all the websocket-connected
   * participants in JSON format, and out on the other bridge connections in binary format. Do not
   * send it out on the connection from which it came; that would create a routing loop
   * 
   * @param aPdu 
   */
  public void receiveFromNveBinary(byte[] data, NveSocket sender)
  {
      
      //System.out.println("servlet got PDU from binary format bridge");
      
      PduFactory factory = new PduFactory();
      Pdu aPdu = factory.createPdu(data);
      //System.out.println("Got bridged binary PDU of type " + aPdu.getPduType());
      
      if(aPdu instanceof EntityStatePdu)
      {
          try
          {
              EntityStatePdu espdu = (EntityStatePdu)aPdu;
              EntityID eid = espdu.getEntityID();
              
              // Convert it to JSON
              ObjectMapper objectMapper = new ObjectMapper();
              String jsonFormatEspdu = objectMapper.writeValueAsString(espdu);
              
              // Send it to all the web-connected clients in JSON format
              Iterator<NveSocket> iterator = connections.iterator();
              while(iterator.hasNext())
              {
                  NveSocket aConnection = iterator.next();
                  
                  // Web client? repeat the message out in JSON format, unless the connection in question
                  // was the one that sent it in the first place. (We don't want routing loops)
                  if(aConnection instanceof NveDisJsonSocket)
                  {
                      if(!aConnection.equals(sender))
                      {
                        ((NveDisJsonSocket)aConnection).sendJsonMessage(jsonFormatEspdu);
                      }
                  }
                  
                  // Binary client? Send it out in binary format
                  if(aConnection instanceof NveDisBinarySocket)
                  {
                      if(!aConnection.equals(sender))
                      {
                          ((NveDisBinarySocket)aConnection).connection.sendMessage(data, 0, data.length);
                      }
                  }
                 
              }
              
          }
          catch(Exception e)
          {
              System.out.println(e);
          }
          
      }
      
  }
  
  /** One of the web clients sent a message to the server in text JSON format.
   * Send it out to all the other participants, but not the sender.
   */
  
  public void receiveFromNveJson(String jsonFormatPdu, NveSocket sender)
  {
      
      //System.out.println("servlet got PDU from JSON format web client");
      
      EntityStatePdu aPdu = null;
      byte[] binaryData = null;
     
      try
      {
          ObjectMapper objectMapper = new ObjectMapper();
          aPdu = (EntityStatePdu)objectMapper.readValue(jsonFormatPdu, EntityStatePdu.class);
          binaryData = aPdu.marshal();
          
      }
      catch(Exception e)
      {
          System.out.println("Unable to de-JSONize entity state PDU from client, dropping");
          return;
      }
      
      if(aPdu != null)
      {
          try
          {
              EntityStatePdu espdu = (EntityStatePdu)aPdu;
              EntityID eid = espdu.getEntityID();
              
              // Send it to all the web-connected clients in JSON format
              Iterator<NveSocket> iterator = connections.iterator();
              while(iterator.hasNext())
              {
                  NveSocket aConnection = iterator.next();
                  
                  // Web client? repeat the message out in JSON format, unless the connection in question
                  // was the one that sent it in the first place. (We don't want routing loops)
                  if(aConnection instanceof NveDisJsonSocket)
                  {
                      if(!aConnection.equals(sender))
                      {
                        ((NveDisJsonSocket)aConnection).sendJsonMessage(jsonFormatPdu);
                      }
                  }
                  
                  // Binary client? Send it out in binary format
                  if(aConnection instanceof NveDisBinarySocket)
                  {
                      if(!aConnection.equals(sender))
                      {
                          ((NveDisBinarySocket)aConnection).sendBinaryMessage(binaryData);
                      }
                  }
                 
              }
              
          }
          catch(Exception e)
          {
              System.out.println(e);
          }
          
      }
      
  }
  
  /**
   * Send a pdu to all the other participants in the simulation, excluding
   * the sender, plus send it to the native DIS network in DIS format, and any
   * bridges in binary format.
   * 
   * @param aPdu 
   */
  /*
  public void sendPdu(String jsonFormatPdu, NveDisJsonSocket sender)
  {
      // Send the JSON string to all the other participants, excluding
      // the connection that sent the message in the first place
      Iterator<NveDisJsonSocket> iterator = connections.iterator();
      while(iterator.hasNext())
      {
          NveDisJsonSocket aConnection = iterator.next();
          if( !(aConnection.equals(sender)) )
          {
            aConnection.sendJsonMessage(jsonFormatPdu);
          }
      }
      
      // Convert JSON to a PDU, and send it out on the conventional IEEE DIS socket
      try
      {
          ObjectMapper objectMapper = new ObjectMapper();
          EntityStatePdu espdu = (EntityStatePdu)objectMapper.readValue(jsonFormatPdu, EntityStatePdu.class);
          byte[] marshalledData = espdu.marshal();
          nativeConnection.sendData(marshalledData);
      }
      catch(Exception e)
      {
          e.printStackTrace();
          System.out.println(e);
      }
      
      
  }
  * */
 
  /** The http server has handed off the connection to us, the servlet. We do
   * our thing...creating and returning a web socket.
   */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
{
    // If the WebSocket factory accepts the connection, then return. This parses the
    // headers of the http request and figures out whether it's asking for a websocket
    // or something else. If it's asking for a web socket, and everything else looks
    // cool, a websocket object is made available by side effect.
    if (websocketFactory.acceptWebSocket(request,response))
    {
        return;
    }
    
    // Otherwise send an HTTP error.
    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,"Can connect to NVEWebsocket only");
}

public boolean hasEntityID(EntityID eid, NveDisJsonSocket aSocket)
{
    NveSocket socketForEid = entitiesAndConnections.get(eid);
    if(socketForEid == null)
    {
        return false;
    }
    
    return true;
}

public void addEntity(EntityID eid, NveSocket aSocket)
{
    entitiesAndConnections.put(eid, aSocket);
}

public void removeEntitiesForNveSocket(NveSocket aSocket)
{
    Set<Map.Entry<EntityID, NveSocket>> eset = entitiesAndConnections.entrySet();
    Iterator it = eset.iterator();
    while(it.hasNext())
    {
        Map.Entry<EntityID, NveSocket> entry = (Map.Entry<EntityID, NveSocket>)it.next();
        if(entry.getClass().equals(aSocket))
        {
            eset.remove(entry.getKey());
        }
    }
}

    
}
