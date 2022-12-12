
package edu.nps.moves.websockets;

import org.eclipse.jetty.websocket.WebSocket;

/**
 * Common superclass for NVEJsonSocket and NVEBinarySocket
 * @author DMcG
 */
public class NveSocket 
{
    /** Hand out unique IDs to the clients as they connect.*/
    private static int nextNveId = 0;
    
    int nveSocketId;
    
    /** Websocket.Connection interface */
    volatile WebSocket.Connection connection;
    
    /** Hook back to the servlet */
    NveServlet servlet;
    
    public static synchronized int getNextId()
    {
        int id = nextNveId;
        nextNveId++;
        return nextNveId;
    }
    
    public NveSocket(NveServlet servlet)
    {
        this.servlet = servlet;
        this.nveSocketId = NveSocket.getNextId();
    }
    
    public int getNveSocketId()
    {
        return nveSocketId;
    }
    
    public WebSocket.Connection getConnection()
    {
        return connection;
    }
    
    public String toString()
    {
        return this.getClass().getName() + ": " + nveSocketId;
    }
    
}
