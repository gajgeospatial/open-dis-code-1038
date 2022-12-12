//heartbeat function
var disUpdate = true;
var heartbeat = function() 
{
	if(disUpdate)
	{
		//console.log("heartbeat");
		// use this to send in JSON format
		//websocket.send(JSON.stringify(ourEntity.lastEspdu));
		//update Our Entity Mesh Location

		// Use this to send in standard IEEE DIS binary format
		var dataBuffer = new ArrayBuffer(1500);
		var outputStream = new dis.OutputStream(dataBuffer);
		ourEntity.lastEspdu.encodeToBinaryDIS(outputStream);
		var trimmedData = dataBuffer.slice(0, outputStream.currentPosition);
		websocket.send(trimmedData);
	}
};


