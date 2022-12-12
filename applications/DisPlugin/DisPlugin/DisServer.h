#pragma once

// Shut up about HawkNL use of wcstombs
#pragma warning(disable:4996)
#pragma warning(disable:4251)

#include <stdlib.h>
#include <iostream>
#include <string>
#include <nl.h> // HawkNL
#include <DIS/Pdu.h>
#include <DIS/Vector3Double.h>

// Typical Ethernet MTU frame size is 1500. It's possible that PDUs
// can be bigger than this--up to 8K--but somewhat rare.
#define MTU_SIZE 1500

/**
 * Represents a DIS server, which contains a socket that can receive and decode DIS messages
 * from the network.
 *
 * @author DMcG
 */

class DisServer
{

	/** UDP multicast socket */
	NLsocket socket;

	NLthreadID threadID;

	
	/** where we send PDUs when we receive them */
	//PduProcessor *pduReceiver;


public:
	DisServer(std::string multicastAddress, unsigned short port);
	~DisServer(void);

	bool sendPdu(DIS::Pdu& aPdu);
	void startReadingPdus();
	NLthreadID getReadingThread();
	void readPdu();

	void hawkThreadFunction(void* data);

	void disToLatLonDegrees(DIS::Vector3Double& disCoordinates, // in: Coordinates in DIS
						    DIS::Vector3Double& latLonAlt);     // in/out: Coordinates in lat/lon/alt (degrees)

	void latLonDegreesToDis(DIS::Vector3Double& latLonAlt,      // Coordinates in lat/lon/alt
						    DIS::Vector3Double& disCoordinates);// Coordinates in geocentric (meters)

	/** Sets the object that will be notified when a PDU is received */
	//void setPduReceiver(PduProcessor* receiver);
};
