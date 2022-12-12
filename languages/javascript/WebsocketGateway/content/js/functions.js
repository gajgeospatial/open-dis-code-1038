var script = document.createElement('script');
script.src = "js/dis.js";
document.getElementsByTagName('script')[0].parentNode.appendChild(script);

function sendPdu(pdu)
{
	var dataBuffer = new ArrayBuffer(1500);
	var outputStream = new dis.OutputStream(dataBuffer);
	pdu.encodeToBinaryDIS(outputStream);
	var trimmedData = dataBuffer.slice(0, outputStream.currentPosition);
	websocket.send(trimmedData);
};

function sendP2pPdu(pdu, sendFn)
{
	var dataBuffer = new ArrayBuffer(1500);
	var outputStream = new dis.OutputStream(dataBuffer);
	pdu.encodeToBinaryDIS(outputStream);
	var trimmedData = dataBuffer.slice(0, outputStream.currentPosition);
	sendFn(trimmedData);
};

function spliceCollidersArray(colliders, object)
{
	var indexNumber = colliders.indexOf(object);
	colliders.splice(indexNumber,1);
};

