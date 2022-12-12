#pragma once

#include <fstream>
#include <iostream>
#include <nl.h> // HAWK NL networking library

#include "DisServer.h"

#include <DIS/Pdu.h> // open-dis
#include <DIS/EntityStatePdu.h>
#include <DIS/SetDataPdu.h>
#include "ConfigFile.h"

/** A singleton pattern object that represents plugin-wide, single-instance
 * data about the DIS plugin. Created in the startPlugin() method and used
 * thereafter.
 *
 * @author DMcG
 */

using namespace std;

#define PI (3.1415926553)

class PluginSingleton
{
private:
	static bool instanceFlag;
	static PluginSingleton *instance;
	ofstream logFile;
	DisServer* disServer;

	DIS::EntityStatePdu aircraftEspdu;
	DIS::EntityID targetEID;

	DIS::EntityStatePdu missile_1Espdu;
	bool missile_1Launched;

	DIS::EntityStatePdu missile_2Espdu;
	bool missile_2Launched;

	DIS::EntityStatePdu missile_3Espdu;
	bool missile_3Launched;

	DIS::EntityStatePdu missile_4Espdu;
	bool missile_4Launched;

	static const int PORT = 62040; // Port used 

	/** Note, private constructor. To get the instance, call getInstance().*/
	PluginSingleton(void);
	void loadEntityInfo(ConfigFile& config, const char* section, DIS::EntityStatePdu& espdu);
	void loadEntityID(ConfigFile& config, const char* section, DIS::EntityID& eid);

public:
	
	~PluginSingleton(void);

	/** Returns the single, shared instance */
	static PluginSingleton* getInstance();

	/** Logging */
	void log(const char* logString);

	/** Send PDU */
	void sendPdu(DIS::Pdu &aPdu);

	/** Iniitialize the first missile on the missile server (a separate program) by 
	 * sending a series of SetDataPdus to it 
	 */
	void initializeMissile_1(DIS::SetDataPdu& initializationPdu);

	/** Create a SetDataPdu structured to interact with the China Lake missile server. 
	 * this has data sent over the umbilical cable before missile launch.
	 */
	void initializeUmbilicalDataPdu(DIS::SetDataPdu& setDataPdu);

	/** Create a SetData launch PDU for use with the China Lake missile server. This 
	 * causes a launch of the missile, after which no umbilical data can be recieved.
	 */
	void initializeLaunchDataPdu(DIS::SetDataPdu& launchDataPdu);

	void initializeAndLaunchMissile();


	/** Flight loop callback */
	float flightLoopCallback(float                inElapsedSinceLastCall,    
                            float                inElapsedTimeSinceLastFlightLoop,    
                            int                  inCounter,    
                            void *               inRefcon);
};
