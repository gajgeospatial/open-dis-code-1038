/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nps.moves.websockets;

import org.eclipse.jetty.websocket.WebSocket;

/**
 *
 * @author DMcG
 */
public class NveDisBinarySocket extends NveSocket implements  WebSocket.OnBinaryMessage
{
   
    
    
    public NveDisBinarySocket(NveServlet servlet)
    {
        super(servlet);
    }
    
    
    /** Client has connected. Add it to a list of all clients. 
     */
    @Override
    public void onOpen(Connection connection)
    {
        System.out.println("onOpen method called in NveBinaryDisSocket, id=" + nveSocketId);
        this.connection=connection;
        
        // Add us to the list of all client connections maintained by the servlet
        servlet.addNveConnection(this);
    }
    
    @Override
    public void onClose(int closeCode, String message)
    {
        System.out.println("onClose method called in NveBinaryDisSocket");
        servlet.removeNveConnection(this);
        //positionUpdater.setStopSending(true); // Kill update thread
    }
    
    /** Called when a binary message is sent. This is used only for bridging with
     * other Java or non-Javascript langages. The web browser clients should be sending
     * JSON text messages.
     */
    @Override 
    public void onMessage(byte[] data, int offset, int length)
    {
        // It's not documented in the Jetty docs (thanks, guys!) but the data received is prepended with
        // some cruft. The offset value tells you how var you have to go into the data to get to the
        // start of the payload.
        byte[] payload = new byte[length];
        System.arraycopy(data, offset, payload, 0, length);
        servlet.receiveFromNveBinary(payload, this);
    }
    
    public void sendBinaryMessage(byte[] data)
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
