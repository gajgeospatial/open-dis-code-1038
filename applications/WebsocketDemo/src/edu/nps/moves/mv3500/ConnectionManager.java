

package edu.nps.moves.mv3500;

import java.util.*;
import java.util.concurrent.*;
import org.json.*;

/**
 * Manages the various connections established by web pages.
 * 
 * We need to be careful about concurrency here--things are happening
 * async and we can be multithreaded. This is a singleton.
 * 
 * @author DMcG
 */
public class ConnectionManager 
{
    /** Singleton */
    public static ConnectionManager connectionManager = null;
    
    /** Every connection established by a web page is kept in this list. This should
     * be a concurrentHashSet, but there doesn't seem to be one out of the box.
     */
    public  ConcurrentMap<WebPageConnection, WebPageConnection> connections;
    
    /** Use this to get the single, shared connectionManager instance
     * @return the single, shared instance of ConnectionManager
     */
    public static synchronized ConnectionManager getConnectionManager()
    {
        if(connectionManager == null)
        {
            connectionManager = new ConnectionManager();
        }
        
        return connectionManager;
    }
    
    /** Use the static method above to retrieve the single, shared connection manager
     */
    private ConnectionManager()
    {
         this.connections = new ConcurrentHashMap<>(); 
         
         // Create a server-side task that periodically sends
         // messages to all the clients.
         
         /*
         TestMessageSender trafficSender = new TestMessageSender();
         Thread senderThread = new Thread(trafficSender);
         senderThread.start();
         */
    }
    
    /**
     * Add a client connection to the list. Messages will be
     * relayed to this client.
     * 
     * @param aWebSocket 
     */
    public synchronized void addConnection(WebPageConnection aWebSocket)
    {
            connections.put(aWebSocket, aWebSocket);
    }
    
    /**
     * Remove a connection from the list of active connections. This is
     * typically called when a client closes the connection. We remove it
     * from the list of active connections.
     * 
     * @param aWebSocket 
     */
    public synchronized void removeConnection(WebPageConnection aWebSocket)
    {
          connections.remove(aWebSocket);
    }
    
    /**
     * Send a message we have received from the sender out to all the other
     * clients, but do NOT send it back to the client that sent it. Typically
     * the message is in string JSON format.
     * 
     * @param message message received from sender
     * @param sender the client that sent the message. The message is not sent to this client
     */
    public void repeatMessage(String message, WebPageConnection sender)
    {
            try
            {
                // Optionally we can convert the string into a Java JSON
                // object and examine it for various reasons, just as 
                // area of interest management.
                //JSONObject jsonMessage = new JSONObject(message);
                
                Iterator it = connections.keySet().iterator();
                while(it.hasNext())
                {
                   WebPageConnection aWebSocket = (WebPageConnection)it.next();

                    // Repeat the message to all clients, except the client that sent it.
                    if(!aWebSocket.equals(sender))
                    {
                        aWebSocket.sendToClient(message);
                    }
                }
            }
            catch(Exception e)
            {
               System.out.println(e);
               e.printStackTrace();
            }
        
    }

}
