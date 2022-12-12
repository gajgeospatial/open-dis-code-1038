
package edu.nps.moves.mv3500;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.resource.FileResource;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a full-blown web server that happens to also handle websocket
 * connections on the server side. Websocket connections are handed off
 * to a subclass for handling.
 * 
 * Lifted from http://amilamanoj.blogspot.com/2013/06/secure-websockets-with-jetty.html
 * 
 * @author DMcG
 */
public class WebSocketServer 
{
    /** The default port the webserver listens on. */
    public static final int SERVER_PORT = 8282;
    
    /** The web server we're creating */
    private Server server;
   
    /** Port to listen on */
    private int    port;
    
    /** The handlers--the type of content the web server can serve up */
    private static List<Handler> webSocketHandlerList = new ArrayList<>();

    /** 
     * Entry point. Create a new Server class, initialize it, and start it.
     */
    public static void main(String[] args) throws Exception 
    {
        
        // Basic Jetty server
        Server server = new Server();
        
        // Add handlers for the various things a web server can do: basic
        // http, websockets, etc.
        
        // Http server
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(httpConfiguration));
        http.setPort(SERVER_PORT);
        
        // Tell server about connections
        Connector[] connectors = {http};
        server.setConnectors(connectors);
        
        // Set up a websocket handler. Incoming requests to ws://
        // will be handed off to this class.
        WebSocketHandler wsHandler = new WebSocketHandler() 
        {
            @Override
            public void configure(WebSocketServletFactory webSocketServletFactory) 
            {
                webSocketServletFactory.register(WebPageConnection.class);
            }
        };
        
        // Add it to the handler list
        ContextHandler wsContextHandler = new ContextHandler();
        wsContextHandler.setHandler(wsHandler);
        //wsContextHandler.setContextPath("/nve");  // this context path doesn't work ftm
        webSocketHandlerList.add(wsHandler);
        
        // Add a static content (html) handler. Html and other files
        // go in the content directory.
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[] {"index.html", "index.htm"} );
        resourceHandler.setResourceBase("./content");
        webSocketHandlerList.add(resourceHandler);
        
        // Add a JSP handler. JSP can be handy for determining the internal
        // state of this server from a web page.
        WebAppContext jspContext = new WebAppContext();
        jspContext.setWelcomeFiles(new String[]  {"index.jsp"});
        jspContext.setResourceBase("./content");
        jspContext.setContextPath("/");
        webSocketHandlerList.add(jspContext);
        
        // Create a default handler for everything else
        DefaultHandler defaultHandler = new DefaultHandler();
        webSocketHandlerList.add(defaultHandler);
       
       // Add the handlers we created above to the server. The order in which they're
       // added is significant; the web server travels down the handler list until
       // it finds a match.
        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(webSocketHandlerList.toArray(new Handler[0]));
        server.setHandler(handlerCollection);

        // We've configured the web server to manage several types of content: html,
        // jsp pages, and web sockets.
        
        // Start the http server
        System.out.println("Starting websocket server");
        server.start();
        server.join();
    }

   


}
