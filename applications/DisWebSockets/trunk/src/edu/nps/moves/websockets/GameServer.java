package edu.nps.moves.websockets;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.plus.servlet.ServletHandler;
import org.eclipse.jetty.util.resource.Resource;

import edu.nps.moves.dismobile.*;
import java.util.Properties;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * Sets up a web server that can accept, from the client, a web socket connection.
 * Uses various jetty jars for all the functionality. This version of Jetty,
 * 7.5.4, uses version 13 of the web sockets standard. The various browsers
 * are a twisty maze of version issues.<p>
 * 
 * Jetty is a servlet container; servlet containers can also act as web servers.
 * This sets up a web server, then registers a servlet to handle web socket
 * connections. 
 * 
 * The version 13 web socket standard is here: http://tools.ietf.org/html/draft-ietf-hybi-thewebsocketprotocol-13<p>
 * 
 * This code is closely modeled on the example here: http://webtide.intalio.com/2011/08/websocket-example-server-client-and-loadtest/
 *
 * @author DMcG
 */
public class GameServer
{

    /** TCP Port 8080 is the traditional place for servlet containers such as Tomcat to listen. */
    public static final int WEBSOCKET_PORT = 8081;
    
    

    
    
    /** This is the entry point that sets up the server; clients, in the web
     * browser, contact this server and receive a websocket back.
     * 
     * @param args 
     */
    public static void main(String args[])
    {
        try
        {
            // Create a new server and a servlet that handles all the details
            // of setting up a websocket on the server side
            Server server = new Server(WEBSOCKET_PORT);
            
            // Create a servlet that will handle incoming connections create web sockets.
            // The servlet handler is a jetty class that registers servlets for the 
            // jetty container to run. The "servlet handler", can 
            // manage several servlets, each with their own purpose. In this case
            // we're registering only one servlet. But we could for example create
            // a servlet to handle the DIS protocol in addition to the made-up NVE
            // protocol, and clients could ask for that. You can also have sub-protocols,
            // ie "/nve/v2/". In this case we register two handlers. The "nve" protocol
            // is intended to handle only JSONized DIS packets, while the "nvemeta"
            // protocol is intended to handle any other message we intend to dream up.
            
           ServletHandler servletHandler = new ServletHandler();
           servletHandler.addServletWithMapping(NveServlet.class, "/nve/*");
           servletHandler.addServletWithMapping(NveServlet.class, "/nveb/*");
           

           // Since this is a full-on web server, we can have lots of different requests
           // come in. The above lets us handle requests for nve websockets. We can also
           // handle requests for other types of things, like http pages, javascript, etc.
           
           // Create a resource handler for static content (eg index.html, foo.js, bar.css)
           // This allows you to host various static content pieces, like Javascript or css,
           // on the server side--in this case in the directory "content" of the project.
           // You can connect with http://localhost:WEBSOCKET_PORT/myFile.html
           ResourceHandler resourceHandler = new ResourceHandler();
           resourceHandler.setDirectoriesListed(true);
           resourceHandler.setWelcomeFiles(new String[] {"index.html"});
           //resourceHandler.setBaseResource(Resource.newClassPathResource("content"));
           resourceHandler.setResourceBase("content"); 
           
           
           // Create a default handler for all other requests. Order is important here; if the above
           // handlers fall through the array provided by the setHandlers call below it will be received by this.
           DefaultHandler defaultHandler = new DefaultHandler();
           
           // Set the handlers on the server as a handler list. Order can be important; the 
           // handlers are called in order to see which one can receive requests.
           HandlerList handlers = new HandlerList();
           handlers.setHandlers(new Handler[] {servletHandler, resourceHandler, defaultHandler});
           server.setHandler(handlers);
           
            // Start the server
            server.start();
            System.out.println("web Server started and listening on port " + WEBSOCKET_PORT);
            
            
            System.out.println("Starting conventional DIS network connection");
            Properties netConnectionProperties = new Properties();
            // What mcast address we listen on for PDUs
            netConnectionProperties.put("destinationAddress", "239.1.2.3");
            netConnectionProperties.put("port", "62040");
            netConnectionProperties.put("destinationPort", "62040");
            netConnectionProperties.put("timeToLive", "2");
            netConnectionProperties.put("connectionType", "udpMulticast");
            netConnectionProperties.put("websocketUrl", "ws://localhost:8081/nveb");

    
            DisBridge localConnection = new DisBridge(netConnectionProperties);
            
            
            server.join();
            
            
            
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

}
