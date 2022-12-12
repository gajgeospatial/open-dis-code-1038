
package edu.nps.moves.websockets;

import java.io.*;
import java.util.*;
import edu.nps.moves.net.*;
import edu.nps.moves.dismobile.*;
import edu.nps.moves.disutil.*;

import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;
import org.eclipse.jetty.websocket.WebSocketHandler;
import org.eclipse.jetty.websocket.WebSocketConnection;
import org.eclipse.jetty.websocket.WebSocket;
import java.util.concurrent.TimeUnit;
import java.net.URI;



/**
 * Forwards DIS traffic from the local network to a given websocket URL.
 * 
 * @author DMcG
 */
public class DisBridge implements PduReceiver
{
    /** Contains the settings for listening to the local network, and where to forward */
    Properties           localConnectionProperties;
    
    /** Websocket connection to the place we're bridging to */
    WebSocketClient      websocketClient = null;
    
    /** actual connection */
    WebSocket.Connection connection;
    
    /** the local native network connection */
    NetConnectionMulticast        localConnection = null;
    
    /** EntityIDs that are NOT on the local native interface. We use this to
     * prevent routing loops 
     */
    HashSet<EntityID> entityIDsFromOutside;
    
    PduFactory pduFactory;
    
    public DisBridge(Properties props)
    {
        this.localConnectionProperties = props;
        entityIDsFromOutside = new HashSet<EntityID>();
        pduFactory = new PduFactory();
        
        NetConnectionDescription connectDescription = new NetConnectionDescription(localConnectionProperties);
        NetConnectionFactory connectionFactory = new NetConnectionFactory();
        localConnection = (NetConnectionMulticast)connectionFactory.netConnectionForDescription(connectDescription);
        localConnection.setPduReceiver(this);
        
        // Set up the websocket connection to the server
        try
        {
            WebSocketClientFactory factory = new WebSocketClientFactory();
            factory.setBufferSize(1024 * 8);
            factory.start();
            
            websocketClient = factory.newWebSocketClient();
            websocketClient.setMaxIdleTime(300000);
            websocketClient.setMaxTextMessageSize(1024 * 8);
            websocketClient.setMaxBinaryMessageSize(1024 * 8);
            websocketClient.setProtocol("nveb");
            
            connection = websocketClient.open(new URI(localConnectionProperties.getProperty("websocketUrl")), new WebSocket.OnBinaryMessage()
            {
                @Override
                 public void onOpen(WebSocket.Connection connection)
                 {
                   System.out.println("Opened binary websocket");
                 }

                @Override
                 public void onClose(int closeCode, String message)
                 {
                   System.out.println("closed binary websocket");
                 }

                /** Got message forwarded from websocket, from the other side of the bridge. Send it out on the local
                 * network interface. Also note the packet, because we don't want to
                 * send incoming packets back out again.
                 */
                @Override
                 public void onMessage(byte[] data, int offset, int length)
                 {
                     // The jetty docs don't mention this--bitter, me?--but the binary data is prepended with some
                     // framing data cruft. The actual payload starts offset bytes into the data.
                     byte[] payload = new byte[length];
                     System.arraycopy(data, offset, payload, 0, length);
                     
                     Pdu aPdu = pduFactory.createPdu(payload);
                     if((aPdu != null) && (aPdu instanceof EntityStatePdu))
                     {
                         EntityID id = ((EntityStatePdu)aPdu).getEntityID();
                         entityIDsFromOutside.add(id);
                     }
                     
                     DisBridge.this.localConnection.sendData(payload);
                 }

            }).get(20, TimeUnit.SECONDS);
              
                 }
                 catch(Exception e)
                 {
                     System.out.println(e);
                     System.out.println("Can not open connection to binary websocket URL");
                 }
        
        
    }

    public void waitOnNetworkThread()
    {
        // This needs to be fixed
        try
        {
            ((NetConnectionMulticast)localConnection).readThread.join();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    /** A pdu has been received from the local network; forward it to the websocket */
    @Override
    public void receivePdu(byte[] data)
    {
        Pdu aPdu = (EntityStatePdu)pduFactory.createPdu(data);
        if((aPdu != null) && (aPdu instanceof EntityStatePdu))
        {
            EntityStatePdu espdu = (EntityStatePdu)aPdu;
            EntityID id = espdu.getEntityID();
            if(!(entityIDsFromOutside.contains(id)))
            {
                try
                {
                     connection.sendMessage(data, 0, data.length);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        NetConnection nativeConnection;
        DisBridge localBridge;
        Properties netConnectionProperties = null;
        
        // No args--connect to the default endpoint
        if(args.length == 0)
        {
            netConnectionProperties = new Properties();
            netConnectionProperties.put("destinationAddress", "239.1.2.3");
            netConnectionProperties.put("port", "62040");
            netConnectionProperties.put("destinationPort", "62040");
            netConnectionProperties.put("timeToLive", "2");
            netConnectionProperties.put("connectionType", "udpMulticast");
            netConnectionProperties.put("websocketUrl", "ws://oam.nps.edu:80/nveb");
        }
        
        // One argument--should be a properties file
        if(args.length == 1)
        {
            try
            {
                FileInputStream propertiesFile = new FileInputStream(args[0]);
                netConnectionProperties = new Properties();
                netConnectionProperties.load(propertiesFile);
            }
            catch(Exception e)
            {
                System.out.println("Cannot open properties file " + args[0]);
                System.exit(-1);
            }
            
            
        }
        
        if(args.length > 1)
        {
            System.out.println("Use: java -jar build/DisBridge.jar <propertiesFile>");
            System.exit(-1);
        }

        DisBridge newBridge = new DisBridge(netConnectionProperties);
        
        newBridge.waitOnNetworkThread();
        
        
    }
    
    
    
}
