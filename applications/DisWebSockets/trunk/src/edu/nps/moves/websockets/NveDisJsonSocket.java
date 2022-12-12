
package edu.nps.moves.websockets;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketFactory;
import org.codehaus.jackson.map.ObjectMapper;
import edu.nps.moves.dismobile.*;


/**
 * The logic for a specific type of websocket. In this case an "Networked Virtual 
 * Environment" web socket that pushes back JSON object position updates to the client.
 * This also contains a list of binary format websockets, which are used to bridge
 * remote networks. 
 * <p>
 * 
 * @author mcgredo
 */
public class NveDisJsonSocket extends NveSocket implements WebSocket.OnTextMessage
{
    
    
    /** All the entityIDs from which we have _received from the client_
     * on this connection. This means those EIDs are hosted on that client,
     * and we should not send out PDUs to that client.
     */
    
    public NveDisJsonSocket(NveServlet servlet)
    {
        super(servlet);
    }
    
    @Override
    public boolean equals(Object otherObject)
    {
        if( !(otherObject instanceof NveDisJsonSocket) )
            return false;
        
        NveDisJsonSocket otherSocket = (NveDisJsonSocket)otherObject;
        
        if(nveSocketId == otherSocket.getNveSocketId())
            return true;
        
        return false;  
    }
    
    /** Client has connected. Add it to a list of all clients. 
     */
    @Override
    public void onOpen(Connection connection)
    {
        System.out.println("onOpen method called in NVESocket, id=" + nveSocketId);
        this.connection=connection;
        
        // Add us to the list of all client connections maintained by the servlet
        servlet.addNveConnection(this); 
    }
   
    /** Callback for when a WebSocket connection is closed.<p>
     * 
     * Remove this WebSocket from the members set and shut down the updater thread
     */
    @Override
    public void onClose(int closeCode, String message)
    {
        System.out.println("onClose method called in NVEWebSocket");
        servlet.removeNveConnection(this);
        //positionUpdater.setStopSending(true); // Kill update thread
    }
    
    /** Callback for when a WebSocket message is received from client */
    @Override
    public void onMessage(String data)
    {
        //System.out.println("Got message from client, " + data);
        
        try
        {
            
            // Echo  the JSON format out to the other participants and any bridges
            servlet.receiveFromNveJson(data, this);
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Could not deserialize JSON:" + data);
        }
      
    }
    
    
    
    public void sendJsonMessage(String jsonFormatMessage)
    {
        try
        {
            connection.sendMessage(jsonFormatMessage);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
