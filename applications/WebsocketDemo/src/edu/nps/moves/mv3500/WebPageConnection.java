/**
 * Lifted from http://amilamanoj.blogspot.com/2013/06/secure-websockets-with-jetty.html
 */

package edu.nps.moves.mv3500;

/**
 * A class that corresponds to one connection to one client. This is created when
 * a client connects to us, one instance per connection. Typically there's one
 * socket opened from the web page to the server.
 * 
 * @author DMcG
 */

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import org.json.*;
import java.nio.*;

import java.io.IOException;

@WebSocket
public class WebPageConnection 
{
    /** The far side of the connection */
    private RemoteEndpoint remote;
   

    /** Fired when the websocket from the client connects. Add it to the ConnectionManager's
     * list of connections.
     * 
     * @param session The session with the web client
     */
    @OnWebSocketConnect
    public void onConnect(Session session) 
    {
        //System.out.println("WebSocket Opened");
        this.remote = session.getRemote();
        
        ConnectionManager.getConnectionManager().addConnection(this);
    }

    /** Fired when the client sends a message. Tell the connection manager to repeat the 
     * message to all other clients, but do not loop it back to the sender.
     * 
     * @param message the (text) message sent from the client to the server, typicall in JSON format
     */
    @OnWebSocketMessage
    public void onMessage(String message) 
    {
        //System.out.println("Message from Client: " + message);
        ConnectionManager.getConnectionManager().repeatMessage(message, this);
       
    }

    /** Fired when the web socket closes. The client informs us, and we remove
     * the client from the list of active clients in ConnectionManager
     * 
     * @param statusCode reason for closure
     * @param reason string reason for closure
     */
    @OnWebSocketClose
    public void onClose(int statusCode, String reason) 
    {
            System.out.println("WebSocket Closed. Code:" + statusCode + " Reason:" + reason);
            ConnectionManager.getConnectionManager().removeConnection(this);
    }
    
    /**
     * Sends data in string format to the client. This is typically in
     * JSON format. This is usually called by the ConnectionManager.
     * 
     * @param message data to be sent
     */
    public void sendToClient(String message)
    {
        try
        {
            remote.sendString(message);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Sends data in binary format to the web client. The client should specify
     * how it wants to receive the data, in either an arraybuffer or a blob.
     * 
     * @param buf 
     */
    public void sendBinaryToClient(byte[] buf)
    {
        try
        {
            remote.sendBytes(ByteBuffer.wrap(buf));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

}
