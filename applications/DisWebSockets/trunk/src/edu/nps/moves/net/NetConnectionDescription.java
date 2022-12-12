
package edu.nps.moves.net;

import java.io.StringReader;
import java.net.*;
import java.util.Properties;

/**
 * Describes a connection to some sort of place we can send PDUs to or from which we can
 * receive PDUs.<p>
 * 
 * General format is "attribute:value|attribute:value|attribute:value" for the string
 * format. Once ingested it gets turned into a properties file. <p>
 * 
 * The properties must include a 
 * 
 * @author mcgredo
 */
public  class NetConnectionDescription extends Object
{
    public enum ConnectionType  {
                                    UDP_UNICAST, 
                                    UDP_BROADCAST,  
                                    UDP_MULTICAST, 
                                    TCP, 
                                    WEBSOCKET
                                };
    
    Properties connectionProperties;
    ConnectionType connectionType;
    
    /** Returns a string description of the network connection, eg "type=datagram.multicast,port=64020,multicastgroup=239.1.2.3"
     * 
     * @return String description of the network connection
     */
    @Override
    public  String toString()
    {
        return connectionProperties.toString();
    }
    
    private ConnectionType connectionTypeFromString(String aConnectionTypeString)
    {
        ConnectionType ct;
        
       if(aConnectionTypeString.equalsIgnoreCase("udpUnicast"))
                ct = ConnectionType.UDP_UNICAST;
            else if( aConnectionTypeString.equalsIgnoreCase("udpMulticast"))
                ct = ConnectionType.UDP_MULTICAST;
            else if( aConnectionTypeString.equalsIgnoreCase("udpBroadcast"))
                ct = ConnectionType.UDP_BROADCAST;
            else if( aConnectionTypeString.equalsIgnoreCase("tcp"))
                ct = ConnectionType.TCP;
            else if(aConnectionTypeString.equals("websocket"))
                ct = ConnectionType.WEBSOCKET;
            else 
            {
                System.out.println("No valid connection type property provided, invalid connection string");
                return null;
            }
    
       return ct;
    }
    
    
    public NetConnectionDescription(String netConnectionString)
    {
        try
        {
             this.connectionProperties = new Properties();
             this.connectionProperties.load(new StringReader(netConnectionString));

             // It would be nice to chain constructors here, but that must be the first line
             String cType = connectionProperties.getProperty("connectionType");
            
            if( cType == null)
            {
                System.out.println("missing data connection  type in connection string " + connectionProperties.toString());
            }

            connectionType = this.connectionTypeFromString(cType);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
     
    }
    
    public NetConnectionDescription(Properties connectionProperties)
    {
        this.connectionProperties = connectionProperties;
        
        String cType = connectionProperties.getProperty("connectionType");
            
        if( cType == null)
        {
            System.out.println("missing data connection  type in connection string " + connectionProperties.toString());
        }

        connectionType = this.connectionTypeFromString(cType);
        
        
    }
    
}
