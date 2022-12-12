#include "StdAfx.h"
#include "DisServer.h"
#include <DIS/EntityStatePdu.h>
#include <DIS/Pdu.h>
#include <DIS/PduFactory.h>
#include <math.h>

//#include "PduProcessor.h"

// HawkNL, an open source x-platform networking library. see http://hawksoft.com/hawknl
#include <nl.h>

#define PI 3.1415926535897932

#define RADIANS_TO_DEGREES (180.0/ PI)
#define DEGREES_TO_RADIANS (PI / 180.0)

/**
 * This is the entry point for a HawkNL thread that can sit in an infinite
 * loop, reading data from the network. The constructor passes "this" as the
 * argument, and we convert that to a ptr to the newly constructed DIS server.
 */
void *networkReadLoop(void * data)
{
	DisServer ptr = *(DisServer*)data;

	// Give the constructor a chance to finish constructing, just to be safe.
	/*
#ifdef WIN32
	Sleep(100); // 100 ms
#else
	usleep( 100 * 1000); 
#endif
	*/

	// Should never return
	ptr.readPdu();
	
	return NULL;
}


DisServer::DisServer(std::string multicastAddress, unsigned short port)
{
	std::cout << "DIS reading from " << multicastAddress.c_str() << " and port " << (int)port << std::endl;

	//pduReceiver = NULL;
	threadID = (NLthreadID)NL_INVALID;

	NLaddress address; // IP address

	NLboolean success = nlInit(); // Initialize HawkNL library; in the windows world this inits winsock
	if(!success)
	{
		std::cout << "unable to initialize HawkNL library" << std::endl;
	}

	// Unreliable (UDP) sockets
	if(nlSelectNetwork(NL_IP) == NL_INVALID)
	{
		std::cout << " Can't select network type" << std::endl;
	}

	// Default TTL of 1. This means multicast will not go off the local network,
	// even if multicast routing is turned on.
	nlHint(NL_MULTICAST_TTL, NL_TTL_LOCAL);

	// so_resuseaddr, so that multiple processes can open sockets on the same port.
	nlHint(NL_REUSE_ADDRESS, NL_TRUE);

	// Open the multicast socket
	socket = nlOpen(port, NL_MULTICAST);

	if(socket == NL_INVALID)
	{
		std::cout << "Unable to open multicast socket on port " << port << std::endl;
		std::cout << "Error code:" << nlGetSystemError() << std::endl;
	}

	// Do a connect, which will set the default destination address to be the multicast
	// address and port created above. This is a not-so-great way to do things but I 
	// don't see a way to set a per-packet destination address in HawkNL.

	// Set up a default address to send to
	// Convert string representation of the mcast address to a network-world representation;
	// convert std c string object to c string, cast to NLchar ptr.
	if(nlStringToAddr((const NLchar*)(multicastAddress.c_str()), &address) == NL_INVALID)
	{
		std::cout << "Invalid multicast address: " << multicastAddress << std::endl;
	}

	// set port # for the socket. 
	NLboolean setPortResult = nlSetAddrPort(&address, port);
	if(setPortResult == NL_FALSE)
	{
		std::cout << "set port call for address failed" << port << std::endl;
	}

	NLboolean connectResult;
	connectResult = nlConnect(socket, &address);
	if(connectResult == NL_FALSE)
	{
		std::cout << "Unable to connect to socket; no default destination address set" << std::endl;
	}
	else
	{
		std::cout << "connect worked" << std::endl;
	}

	std::cout << "Successfully created DisServer" << std::endl;
}

void DisServer::startReadingPdus()
{
	std::cout << "DIS server told to start reading PDUs" << std::endl;

	// Start a read thread to listen for network traffic. We need a little
	// indirection here, since C++ methods have a hidden method arg of "this".
	// In this case we call a C function, passing this, and then the C function
	// turns around and calls the read function. This sets the joinable feature to
	// TRUE, so we can wait for the infinite loop reading thread to exit.
    threadID = nlThreadCreate(networkReadLoop, this, NL_TRUE);
	if(threadID == ((NLthreadID)NL_INVALID))
	{
		std::cout << " Could not create HawkNL read thread" << std::endl;
		NLint error = nlGetSystemError();
		std::cout << "System error:" << std::endl;
	}
}

NLthreadID DisServer::getReadingThread()
{
	return threadID;
}

DisServer::~DisServer(void)
{
	nlClose(socket);
}
 


/** Send a PDU, of one of several subclasses (eg, entity state, fire, etc). */
bool DisServer::sendPdu(DIS::Pdu& aPdu)
{
	// Create a DIS datastream, which allows us to marshal to a memory buffer.
	// This will marshal to a big-endian format, aka network byte order, the byte order
	// in which DIS packets must be sent.
	DIS::DataStream buffer(DIS::BIG);

	int pduType = aPdu.getPduType();

	aPdu.marshal(buffer);

	// Empty PDU? Don't bother writing
	if(buffer.size()< 1)
		return true;

	int bytesWritten = 0;
	bytesWritten = nlWrite(socket, &buffer[0], buffer.size());

	switch(bytesWritten)
	{
	case 0:
		std::cout << "Network send buffer full" << std::endl;
		break;

	case NL_INVALID: // -1
		std::cout << "Write failed, " << nlGetErrorStr(nlGetError()) << std::endl;
		break;

	default:
		break;
		//std::cout << " Sent data, byte count =" << buffer.size() << std::endl;
	}

    
	return true;
}

void DisServer::readPdu()
{
    //DIS::DataStream buffer(DIS::BIG);
	DIS::PduFactory pduFactory;

	std::cout << "in readPdu()" << std::endl;

	// Typical Ethernet MTU size, plus one byte for detecting overflow.
	// It's possible for some DIS packets to be up to 8K in size, though rare.
	// This will hold the received DIS PDU data, which will later be turned into
	// a PDU object.
	char readBuffer[MTU_SIZE + 1];
	memset(readBuffer, 0, sizeof(readBuffer));

	NLint bytesRead = 0;
	
	while(1==1)
	{
		//std::cout << "In infinite loop" << std::endl;

	bytesRead = nlRead(socket, readBuffer, MTU_SIZE + 1);
	{
		//std::cout << "bytesRead=" << (int)bytesRead << std::endl;

		if(bytesRead == 0)
		{
		}
		else if(bytesRead == NL_INVALID)
		{
			std::cout << "NL_INVALID on socket read" << std::endl;
			// Bogus read for some reason
		}
		else if(bytesRead == MTU_SIZE +1)
		{
			std::cout << " bytes read are too big to fit into the static buffer of size " << MTU_SIZE << std::endl;
			// Too big to fit into our buffer
		}
		else
		{
			/*
			std::cout << " Read packet, bytes of length " << (int)bytesRead << std::endl;
			for(int idx = 0; idx < 150; idx++)
			{
				std::cout << (int)readBuffer[idx] << " ";
			}

			std::cout << std::endl;
			*/

			DIS::Pdu* aPdu = pduFactory.createPdu(readBuffer);

			//if(aPdu != NULL && pduReceiver != NULL)
			{
				//pduReceiver->receivePdu(aPdu);
				//std::cout << "received PDU of type " << (int)aPdu->getPduType() << " length: " << (int)aPdu->getLength() << std::endl;
			}
		}
	}
	
	}
}

/**
 * Converts DIS coordinates (geocentric, center of earth origin, in meters) to lat/lon/altitude.
 * Uses WGS84 for the shape of the earth. This code has been converted back and forth between
 * c and java and c++ a few times.
 */
void DisServer::disToLatLonDegrees(DIS::Vector3Double& disCoordinates, // Coordinates in DIS
								   DIS::Vector3Double& latLonAlt)      // Coordinates in lat/lon/alt (degrees)
{
	double x = disCoordinates.getX();
	double y = disCoordinates.getY();
	double z = disCoordinates.getZ();
	double a = 6378137.0;    // semi-major axis
	double b = 6356752.3142; // semi minor axis

	double rSubN = 0.0;
	double w = sqrt((x*x - b*b));
	double eSquared = (a*a - b*b) / (a*a);
	double ePrimeSquared = (a*a  - b*b) / (b*b);

	double answerLon = 0.0;
	double answerLat = 0.0;
	double answerElevation = 0.0;

	// get longitude
	if(x >= 0)
	{
      answerLon = atan(y/x);
	}
	else if(x < 0.0 && y >= 0.0)
	{
		answerLon = atan(y/x) + PI;
	}
	else
	{
		answerLon = atan(y/x) - PI;
	}

	// Calculate latitude
	double tanBZero = (a * z) / (b * w);
	double bZero = atan(tanBZero);
	double tanPhi = (z + (ePrimeSquared * b * pow(sin(bZero), 3) ) / (w-(a * eSquared * (pow(cos(bZero), 3))) ) );
	double phi = atan(tanPhi);
	answerLat = phi;

	// elevation
	rSubN = (a * a) / sqrt(( a * a) * (cos(phi) * cos(phi)) + ((b * b) * ((sin(phi) * sin(phi)) ) ) );
	answerElevation = (w/cos(phi)) - rSubN;

	answerLat = answerLat * RADIANS_TO_DEGREES;
	answerLon = answerLon * RADIANS_TO_DEGREES;

	latLonAlt.setX(answerLat);
	latLonAlt.setY(answerLon);
	latLonAlt.setZ(answerElevation);
}

void DisServer::latLonDegreesToDis(DIS::Vector3Double& latLonAlt,      // Coordinates in lat/lon/alt (degrees, meters)
								   DIS::Vector3Double& disCoordinates) // Coordinates in geocentric (meters)
{
  double latRadians = latLonAlt.getX() * DEGREES_TO_RADIANS;
  double lonRadians = latLonAlt.getY() * DEGREES_TO_RADIANS;
  double alt = latLonAlt.getZ(); 

  double a = 6378137.0;    // semi-major axis
  double b = 6356752.3142; // sem-minor axis
  double cosLat = cos(latRadians);
  double sinLat = sin(latRadians);

  double rSubN = (a * a) / sqrt((a * a) * (cosLat * cosLat) + ((b * b) * (sinLat * sinLat)));
  double x = (rSubN + alt) * cosLat * cos(lonRadians);
  double y = (rSubN + alt) * cosLat * sin(lonRadians);
  double z = ( ( ((b * b) / (a*a)) * rSubN) + alt) * sinLat;

  disCoordinates.setX(x);
  disCoordinates.setY(y);
  disCoordinates.setZ(z);

}

/*
void DisServer::setPduReceiver(PduProcessor* receiver)
{ 
	pduReceiver = receiver;
}
*/