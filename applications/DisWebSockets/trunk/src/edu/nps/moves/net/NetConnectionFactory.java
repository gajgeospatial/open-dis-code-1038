/*
 *  Copyright (c) 2012, Naval Postgraduate School
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, 
 * this list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright 
 * notice, this list of conditions and the following disclaimer in the 
 * documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package edu.nps.moves.net;

import edu.nps.moves.dismobile.Pdu;

/**
 * A factory object that returns the correct concrete sublcass based on the 
 * inputs.
 * 
 * @author DMcG
 */
public class NetConnectionFactory
{
    public NetConnection netConnectionForDescription(NetConnectionDescription description)
    {
        NetConnection connection = null;
        // This is kind of ugly; it would be nice to use introspection here to return the
        // correct subclass. But then you'd wind up specifying the type of subclass
        // twice, once in the properites and once for the class, which isn't nice either.
        
        switch( description.connectionType)
        {
            case UDP_MULTICAST:
                NetConnectionMulticast cm = new NetConnectionMulticast(description);
                cm.start();
                connection = cm;
                break;
                
            case UDP_BROADCAST:
                NetConnectionBroadcast cb = new NetConnectionBroadcast(description);
                cb.start();
                connection = cb;
                break;
                
            case UDP_UNICAST:
                NetConnectionUnicast cu = new NetConnectionUnicast(description);
                cu.start();
                connection = cu;
                break;
                
            case TCP:
            case WEBSOCKET:
            default:
                System.out.println("Missing network connection type in NetConnectionFactory");
                return null;
        }
        
        return connection;
    }
    
}
