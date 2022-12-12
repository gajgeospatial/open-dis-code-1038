/**
 * Sample node.js program for a binary websocket server. Accepts
 * a message from a web client and sends it to other connected 
 * web clients. 
 * 
 * This code is capable of also listening on the network for native
 * UDP packets on port 3000, but that's turned off for now.
 * 
 * This also saves to a mongodb database, which should be running.
 * The mongodb server install is separate; see mongodb.org for 
 * a download of that server.
 * 
 * @author DMcG
 */

/* Import various node npm packages. These should be installed via
 * 'npm install packageName')
 */

/** Every N messages we send, log some stats on how long
 * it took to broadcast, how many clients were connected, etc.
 * @type Number
 */
var STATISTICS_FREQUENCY = 1000;

/** Native UDP datagrams */
var dgram = require('dgram');

/** DIS implementation */
var dis = require('open-dis');
//var mongoose = require('mongoose');
var mongo = require('mongodb');

/** Websocket support */
var WebSocketServer = require('ws').Server;
var wss = new WebSocketServer({port:8485});

// Set up Mongodb connection
var mongoClient = mongo.MongoClient;
var databaseConnection;

// Connect to the mongodb database, which should be running on 27017. 
// This is a separate install. See http://www.mongodb.org/ for a specific
// installer for mongodb.

// Note that the connection process is async. It will probably finish 
// sometime later, but it will not finish connecting before the line
// of code shown after the 'connect' call.
 
mongoClient.connect('mongodb://localhost:27017/test', function(err, db)
{
    if(!err)
    {
        console.log("MongoDb connected successfully");
    }
    else
    {
        console.log("Cannot connect to MongoDB. Is the server running? Packets will not be logged");
    }
    
    databaseConnection = db;
});

// Stats collection for things such as means, medians, etc. We collect
// stats on how long it takes to send out a full round of messages
// to clients.
require('descriptive-statistics');
var timeToBroadcast = [];

// Factory for converting binary messages into Javascript DIS objects
var pduFactory = new dis.PduFactory();

/** Once we receive a message from a websocket, we repeat
 * it out to all other websockets. It really isn't bcast,
 * since we're simply repeating the message n-1 times, but
 * close enough.
 * 
 * Is the send function async?
 * 
 * @param {type} The DIS message
 * @param {type} The websocket we received the message from. We repeat the message to all websockets but this one.
 */
wss.broadcast = function broadcast(data, ws)
{
    /*
    console.log(data, ws);
    if(ws === 'undefined')
    {
        console.log("repeating message from native UDP");
    }
    */
    
  var startTime = new Date();
  for(var i in this.clients)
  {
      
      // a message from the native udp socket has a ws of null; always
      // repeat to all websockets in that case. If we do know the
      // websocket from which the message came, repeat the message 
      // to all but that ws.
      
      if((ws === 'undefined') || (this.clients[i] !== ws) )
      {
        this.clients[i].send(data);
        //console.log("repeated to client ", i);
      }
  }
  var endTime = new Date();
  
  // Save stats on how long it took to bcast to all clients, periodically log them,
  // then reset them.
  var elapsedTime = endTime.getTime() - startTime.getTime();
  timeToBroadcast.push(elapsedTime);
  if(timeToBroadcast.number >= STATISTICS_FREQUENCY)
  {
      console.log("Number of connections:", Object.keys(this.clients).length, 
                  " Observations:", timeToBroadcast.number, 
                  " Mean:", timeToBroadcast.mean, 
                  " Variance:", timeToBroadcast.variance);
      timeToBroadcast = [];
  }
  
};

/** 
 * What to do on various websocket events.
 * 
 * @param {string} event name
 * @param {function} callback function executed on connection
 */
wss.on('connection', function connection(ws)
{
    // Got a new websocket connection. Set up handlers
    // for various events.
    
    /**
     * Received a message from a client
     * @param {string} message event name
     * @param {function} callback function
     */
    ws.on('message', function incoming(message)
    {
        wss.broadcast(message, ws);
        
        // Save the javascript object to mongodb. The connection
        // might not be set up yet (remember, async) or we may
        // have failed to connect to the mongodb database server.
        if(databaseConnection !== 'undefined')
        {
           // Convert the binary message to a javascript object
           var arraybuffer = toArrayBuffer(message);
           var pdu = pduFactory.createPdu(arraybuffer);
           
           // save to database
           var collection = databaseConnection.collection('dis');
           collection.insert(pdu, function(err, result) {
              if(!err) 
              {
                  //console.log("inserted OK, in wss ", result);
              }
           });
        }
        else
        {
            //console.log("cant insert; in websocket receive; databaseConnection not initialized yet?");
        }
    });
});



// native UDP socket setup. Non-functional  for now. To enable, 
// turn off SHORT_CIRCUIT. There seems to be a problem with the
// content of packets being corrupted.
// 
// There will be a problem with looping packets unless you put some
// sort of marker in the DIS packet to detect packets originally
// sent from this server itself, so they can be discarded.

var nativeUdpSocket = dgram.createSocket('udp4');
nativeUdpSocket.bind(3000);

/**
 * Fired when a UDP packet arrives on the native interface.
 *  
 * @param {string} event type
 * @param {function} callback function to execute when message event fired
 */
nativeUdpSocket.on('message', function(content, rinfo)
{
    var SHORT_CIRCUIT = true;
    if(SHORT_CIRCUIT)
        return;
    
    var arraybuffer = toArrayBuffer(content);
    var none;
    wss.broadcast(arraybuffer, none);

    // Save to database

    if(databaseConnection !== 'undefined')
    {
       var pdu = pduFactory.createPdu(arraybuffer);

       var collection = databaseConnection.collection('dis');
       collection.insert(pdu, function(err, result) {
          if(!err) 
          {
              //console.log("inserted OK in udp native, ", result);
          }
       });
    }
    else
    {
        //console.log("cant insert; databaseConnection not initialized yet?");
    }
    //console.log("Pdu:", pdu);
});

/**
 * Some glue to convert Node's Buffer object to an ArrayBuffer
 */
function toArrayBuffer(buffer)
{
  var ab = new ArrayBuffer(buffer.length);
  var view = new Uint8Array(ab);
  for(var i = 0; i < buffer.length; ++i)
  {
    view[i] = buffer[i];
  }
  return ab;
}




