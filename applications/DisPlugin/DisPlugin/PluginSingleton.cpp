#include "PluginSingleton.h"

#include <iostream>
#include <fstream>

#include <nl.h> // HAWK NL networking library
#include <DIS/EntityStatePdu.h>
#include <DIS/DataStream.h>

#include "XPLMProcessing.h"
#include "XPLMDataAccess.h"
#include "XPLMUtilities.h"
#include "XPLMDisplay.h"
#include "XPLMGraphics.h"

#include "DisServer.h"
#include <DIS/EntityStatePdu.h>
#include <DIS/SetDataPdu.h>
#include "ConfigFile.h"

#include <Windows.h>

//#include "CoordinateTransforms.h"

using namespace std;

/* Data refs we will record. */
XPLMDataRef		gPlaneLat = NULL;
XPLMDataRef		gPlaneLon = NULL;
XPLMDataRef		gPlaneEl = NULL;

/** Aircraft velocity. vx positive is speed means speed east. Vy north, vz up? */
XPLMDataRef     gPlaneLocal_vx = NULL;
XPLMDataRef     gPlaneLocal_vy = NULL;
XPLMDataRef     gPlaneLocal_vz = NULL;
XPLMDataRef     gGroundspeed   = NULL;
XPLMDataRef     gHeading       = NULL;
XPLMDataRef     gPitch         = NULL;
XPLMDataRef     gRoll          = NULL;

int             gTimeSinceLaunch = 0;


bool PluginSingleton::instanceFlag = false;
PluginSingleton* PluginSingleton::instance = 0;

/** Construtor; private, can't be called by outsiders. */
PluginSingleton::PluginSingleton()
   :logFile("xplaneDisPlugin.log")  // initializer for log file
{
	// What missiles have been fired
	missile_1Launched = missile_2Launched = missile_3Launched = missile_4Launched = false;

	// logging
	logFile << "DIS XPlane Log startup" << endl;

	// Open config file to get settings. Normally config file name would  be passed
	// on the command line, but we can't do that, so it's hard coded. The location
	// is in the Xplane directory.
	ConfigFile config("DisPluginConfig.txt");

	std::string configFileMulticastAddress, portString;
    configFileMulticastAddress = config.Value("DIS", "multicastAddress");
	portString = config.Value("DIS", "port");
	int port = atoi(portString.c_str());

	// Local, euclidian coordinate system origin
	std::string latitude = config.Value("DIS", "rangeOriginLatitude");
	std::string longitude = config.Value("DIS", "rangeOriginLongitude");
	std::string altitude = config.Value("DIS", "rangeOriginAltitude");

	logFile << "configured location" << endl;

	disServer = new DisServer(configFileMulticastAddress, (unsigned short)port);

	// Initialize our aircraft and missile DIS entity information
	loadEntityInfo(config, "SELF", aircraftEspdu);
	loadEntityInfo(config, "MISSILE_1", missile_1Espdu);
	loadEntityID(config, "TARGET", targetEID);

	// Some pieces of data in the simulation we need to retrieve
	gPlaneLat = XPLMFindDataRef("sim/flightmodel/position/latitude");
	gPlaneLon = XPLMFindDataRef("sim/flightmodel/position/longitude");
	gPlaneEl  = XPLMFindDataRef("sim/flightmodel/position/elevation");

	gPlaneLocal_vx = XPLMFindDataRef("sim/flightmodel/position/local_vx");
	gPlaneLocal_vy = XPLMFindDataRef("sim/flightmodel/position/local_vy");
	gPlaneLocal_vz = XPLMFindDataRef("sim/flightmodel/position/local_vz");
	gGroundspeed   = XPLMFindDataRef("sim/flightmodel/position/groundspeed");
	gHeading       = XPLMFindDataRef("sim/flightmodel/position/psi"); // true heading, degrees about Z axis (float)
	gRoll          = XPLMFindDataRef("sim/flightmodel/position/phi"); // roll relative to the horizontal in degrees (float)
	gPitch         = XPLMFindDataRef("sim/flightmodel/position/theta"); // pitch roll of aircraft in degrees (float)
}

/** Loads the entityID information from the config file
 */
void PluginSingleton::loadEntityID(ConfigFile& config, const char* section, DIS::EntityID& eid)
{
	std::string val;
	// entity ID
	val = config.Value("DIS", "site");
	eid.setSite(atoi(val.c_str()));

	val = config.Value("DIS", "application");
	eid.setApplication(atoi(val.c_str()));

	val = config.Value(section, "entity");
	eid.setEntity(atoi(val.c_str()));
}

/**
 * Loads the entity type information from the config file. The entity type is bases on
 * the SISO EBV document, a long table of arbitrary numbers that maps those numbers to
 * a specific type of hardware, such as an F-18 or a M1A2 tank or a Sidewinder.
 */
void PluginSingleton::loadEntityInfo(ConfigFile& config, const char* section, DIS::EntityStatePdu& espdu)
{
	std::string val;

	// entity ID
	val = config.Value("DIS", "site");
	espdu.getEntityID().setSite(atoi(val.c_str()));

	logFile << "configured site" << endl;


	val = config.Value("DIS", "application");
	espdu.getEntityID().setApplication(atoi(val.c_str()));

	logFile << "configured application" << endl;


	val = config.Value(section, "entity");
	espdu.getEntityID().setEntity(atoi(val.c_str()));

	logFile << "configured entity" << endl;


	// Entity type
	val = config.Value(section, "kind");
	espdu.getEntityType().setEntityKind(atoi(val.c_str()));

	logFile << "configured kind" << endl;


	val = config.Value(section, "domain");
	espdu.getEntityType().setDomain(atoi(val.c_str()));

	logFile << "configured domain" << endl;


	val = config.Value(section, "country");
	espdu.getEntityType().setCountry(atoi(val.c_str()));

	val = config.Value(section, "category");
	espdu.getEntityType().setCategory(atoi(val.c_str()));

	val = config.Value(section, "subcategory");
	espdu.getEntityType().setSubcategory(atoi(val.c_str()));

	logFile << "configured subcategory" << endl;

	val = config.Value(section, "specific");
	espdu.getEntityType().setSpecific(atoi(val.c_str()));

	logFile << "configured specific" << endl;

}

/** Single way for users to get the single, shared instance. */
PluginSingleton* PluginSingleton::getInstance()
{
	if(!instanceFlag)
	{
		instance = new PluginSingleton();
		instanceFlag = true; 
		return instance;
	}
	else
	{
		return instance;
	}
}

PluginSingleton::~PluginSingleton(void)
{
	// Clean up open files, close open sockets.
	logFile.close();
}

void PluginSingleton::log(const char* logMessage)
{
	logFile << logMessage << endl;
}

/** Send PDU */
void PluginSingleton::sendPdu(DIS::Pdu &aPdu)
{
	//logFile << "in sendPdu, calling disServer" << std::endl;
	disServer->sendPdu(aPdu);

	return;
}

float PluginSingleton::flightLoopCallback(float                inElapsedSinceLastCall,    
                            float                inElapsedTimeSinceLastFlightLoop,    
                            int                  inCounter,    
                            void *               inRefcon)
{
	float	elapsed ;
	double	latitude, longitude, elevation;
	float   vx = 0.0, vy = 0.0, vz = 0.0, groundspeed = 0.0; // Speed east, north, up, meters per second
	float   heading = 0.0, roll = 0.0, pitch = 0.0;
	
	/* The actual callback.  First we read the sim's time and the data. */
		elapsed = XPLMGetElapsedTime();
		
		latitude = XPLMGetDatad(gPlaneLat);
		longitude = XPLMGetDatad(gPlaneLon);
		elevation = XPLMGetDatad(gPlaneEl);
 
		// Velocity over ground. pos x = east, pos y = north, pos z = up. Note these are
		// floats rather than doubles.
		vx = XPLMGetDataf(gPlaneLocal_vx);
		vy = XPLMGetDataf(gPlaneLocal_vy);
		vz = XPLMGetDataf(gPlaneLocal_vz);
		groundspeed = XPLMGetDataf(gGroundspeed);
		heading = XPLMGetDataf(gHeading); // degrees about z axis
		roll = XPLMGetDataf(gRoll);
		pitch = XPLMGetDataf(gPitch);

		aircraftEspdu.getEntityLinearVelocity().setX(vx);
		aircraftEspdu.getEntityLinearVelocity().setY(vy);
		aircraftEspdu.getEntityLinearVelocity().setZ(vz);


		logFile << "Heading:" << heading << " Roll:" << roll << " Pitch:" << pitch << std::endl;

		logFile << "Lat:" << (double)latitude << " Velocity: " << (float)vx << "," << (float)vy << "," << (float)vz << "; " << (float)groundspeed << std::endl;

		// Convert the location to DIS coordinates for use on the wire
		DIS::Vector3Double latLonAlt;
		DIS::Vector3Double disCoords;
		latLonAlt.setX(latitude);
		latLonAlt.setY(longitude);
		latLonAlt.setZ(elevation);

		// convert to DIS coordinates
		disServer->latLonDegreesToDis(latLonAlt, disCoords);

		aircraftEspdu.getEntityLocation().setX(disCoords.getX());
		aircraftEspdu.getEntityLocation().setY(disCoords.getY());
		aircraftEspdu.getEntityLocation().setZ(disCoords.getZ());

		// Orientation. Convert to radians. This SHOULD be converted to global, but we
		// leave in in local for now. Normally radians are measured counter-clockwise from
		// the positive x-axis, but in JAAM they're used like heading: 0 is due north, and
		// clockwise is positive.
		aircraftEspdu.getEntityOrientation().setPsi(heading  * (PI / 180.0));  // heading (radians), zero at due north, positive clockwise
		aircraftEspdu.getEntityOrientation().setPhi(roll * (PI / 180.0));      // roll (radians), 
		aircraftEspdu.getEntityOrientation().setTheta(pitch * (PI / 180.0));   // pitch(radians)

		this->sendPdu(aircraftEspdu);

		//CoordinateTransforms ct;

		//ct.latLonAltToDIS(latitude, longitude, elevation);

		//sprintf(buf, "Since last call: %g Time: %g Lat:%g Lon:%g Elevation:%g",inElapsedSinceLastCall,elapsed, latitude, longitude, elevation);
	    //logFile << buf << endl;

		// Returns time until next call
		return (float)0.1;
}

void PluginSingleton::initializeMissile_1(DIS::SetDataPdu& initializationPdu)
{ 
	this->log("in initializeMissile_1");

	char buf[1500];
	sprintf(buf, "aircraft espdu: %i %i %i\n", aircraftEspdu.getEntityID().getSite(), aircraftEspdu.getEntityID().getApplication(), aircraftEspdu.getEntityID().getEntity());

	this->log(buf);

	// Set up origin (shooter, our plane) and receiving (the missile we're carrying) IDs
	initializationPdu.getOriginatingEntityID().setSite(aircraftEspdu.getEntityID().getSite());
	initializationPdu.getOriginatingEntityID().setApplication(aircraftEspdu.getEntityID().getApplication());
	initializationPdu.getOriginatingEntityID().setEntity(aircraftEspdu.getEntityID().getEntity());

	// Set up the receiver--the target, configured in the static config text file
	initializationPdu.getReceivingEntityID().setSite(missile_1Espdu.getEntityID().getSite());
	initializationPdu.getReceivingEntityID().setApplication(missile_1Espdu.getEntityID().getApplication());
	initializationPdu.getReceivingEntityID().setEntity(missile_1Espdu.getEntityID().getEntity());

	// Add a bunch of data to the SetDataPdu in the variable datums area.
	// This sets up an initialization PDU, with the mode (in variable datum 0) set
	// equal to zero. This is sent to the China Lake JAAM missile server, running
	// in a different process, perhaps a different host.

	// We need to be careful to marshal multi-byte data in big endian (network) byte
	// order, because that's the format in which people receiving it will decode. Note
	// that by default Intel does little endian, so it's easy to screw this up by default.
	DIS::VariableDatum d0;
	d0.setVariableDatumID(0);
	d0.setVariableDatumLength(16); // in bits

	DIS::DataStream dataStream(DIS::BIG);
    dataStream << (short)0; // mode=0
	d0.setVariableDatums(&dataStream[0], 2);
	initializationPdu.getVariableDatums().push_back(d0);

	// missile entity identifier
	DIS::EntityID eid;
	eid.setApplication(missile_1Espdu.getEntityID().getApplication());
	eid.setSite(missile_1Espdu.getEntityID().getSite());
	eid.setEntity(missile_1Espdu.getEntityID().getEntity());
	DIS::DataStream ds1(DIS::BIG);
	eid.marshal(ds1);

	DIS::VariableDatum d1;
	d1.setVariableDatumID(1);
	d1.setVariableDatumLength(48);
	d1.setVariableDatums(&ds1[0], 6);
	initializationPdu.getVariableDatums().push_back(d1);

	// missile type, 1=AIM120 2=AIM-9
	DIS::VariableDatum d2;
	d2.setVariableDatumID(2);
	d2.setVariableDatumLength(16);
	DIS::DataStream ds2(DIS::BIG);
	ds2 << (short)1;
	d2.setVariableDatums(&ds2[0], 2);
	initializationPdu.getVariableDatums().push_back(d2);

	// Missile subtype, 1=AIM120
	DIS::VariableDatum d3;
	d3.setVariableDatumID(3);
	d3.setVariableDatumLength(16);
	DIS::DataStream ds3(DIS::BIG);
	ds3 << (short)1;
	d3.setVariableDatums(&ds3[0], 2);
	initializationPdu.getVariableDatums().push_back(d3);

	// Random number seed
	DIS::VariableDatum d4;
	d4.setVariableDatumID(4);
	d4.setVariableDatumLength(32);
	DIS::DataStream ds4(DIS::BIG);
	ds4 << (int)17;
	d4.setVariableDatums(&ds4[0], 4);
	initializationPdu.getVariableDatums().push_back(d4);

	// Launch station
	DIS::VariableDatum d5;
	d5.setVariableDatumID(5);
	d5.setVariableDatumLength(16);
	DIS::DataStream ds5(DIS::BIG);
	ds5 << (char)'2';
	ds5 << (char)'A';
	d5.setVariableDatums(&ds5[0], 2);
	initializationPdu.getVariableDatums().push_back(d5);

	// Mounting angle
	DIS::VariableDatum d6;
	d6.setVariableDatumID(6);
	d6.setVariableDatumLength(48);
	DIS::DataStream ds6(DIS::BIG);
	DIS::Vector3Float mountingAngle;
	mountingAngle.setX((float)1.0);
	mountingAngle.setY((float)2.0);
	mountingAngle.setZ((float)3.0);
	mountingAngle.marshal(ds6);
	d6.setVariableDatums(&ds6[0], 12);
	initializationPdu.getVariableDatums().push_back(d6);
}

void PluginSingleton::initializeUmbilicalDataPdu(DIS::SetDataPdu& umbilicalDataPdu)
{

	// Set up origin (shooter, our plane) and receiving (the missile we're carrying) IDs
	umbilicalDataPdu.getOriginatingEntityID().setSite(aircraftEspdu.getEntityID().getSite());
	umbilicalDataPdu.getOriginatingEntityID().setApplication(aircraftEspdu.getEntityID().getApplication());
	umbilicalDataPdu.getOriginatingEntityID().setEntity(aircraftEspdu.getEntityID().getEntity());

	umbilicalDataPdu.getReceivingEntityID().setSite(missile_1Espdu.getEntityID().getSite());
	umbilicalDataPdu.getReceivingEntityID().setApplication(missile_1Espdu.getEntityID().getApplication());
	umbilicalDataPdu.getReceivingEntityID().setEntity(missile_1Espdu.getEntityID().getEntity());

	std::vector<DIS::VariableDatum> allDatums = umbilicalDataPdu.getVariableDatums();

	// mode=1 in datumID 0 means this is an umbilical data setDataPdu
	DIS::VariableDatum d0;
    d0.setVariableDatumID(0);
    d0.setVariableDatumLength(16); // in bits
	DIS::DataStream ds0(DIS::BIG);
    ds0 << (unsigned short)1;  // Mode, in this case umbilical data
    d0.setVariableDatums(&ds0[0], 2);
	umbilicalDataPdu.getVariableDatums().push_back(d0);

	// Alignment uncertainty
	DIS::VariableDatum d1;
    d1.setVariableDatumID(101);
    d1.setVariableDatumLength(96); // in bits
	DIS::Vector3Float alignment;
    alignment.setX(1.0f);
    alignment.setY(2.0f);
    alignment.setZ(3.0f);
    DIS::DataStream ds1(DIS::BIG);
    alignment.marshal(ds1);
    d1.setVariableDatums(&ds1[0], 12);
	umbilicalDataPdu.getVariableDatums().push_back(d1);

	// Eject velocity
	DIS::VariableDatum d2;
    d2.setVariableDatumID(102);
    d2.setVariableDatumLength(96); // in bits
	DIS::DataStream ds2(DIS::BIG);
    ds2 << (float)1.0;
	ds2 << (float)2.0;
	ds2 << (float)3.0;
    d2.setVariableDatums(&ds2[0], 12);
    umbilicalDataPdu.getVariableDatums().push_back(d2);

	 // Engagement order number, 0=visual launch, 1-4
	DIS::VariableDatum d3;
    d3.setVariableDatumID(103);
    d3.setVariableDatumLength(32); // in bits
    DIS::DataStream ds3(DIS::BIG);
    ds3 << (int)0; // visual launch
    d3.setVariableDatums(&ds3[0], 4);
    umbilicalDataPdu.getVariableDatums().push_back(d3);
}

void PluginSingleton::initializeLaunchDataPdu(DIS::SetDataPdu& launchDataPdu)
{
	// Set up origin (shooter, our plane) and receiving (the missile we're carrying) IDs
	launchDataPdu.getOriginatingEntityID().setSite(aircraftEspdu.getEntityID().getSite());
	launchDataPdu.getOriginatingEntityID().setApplication(aircraftEspdu.getEntityID().getApplication());
	launchDataPdu.getOriginatingEntityID().setEntity(aircraftEspdu.getEntityID().getEntity());

	launchDataPdu.getReceivingEntityID().setSite(missile_1Espdu.getEntityID().getSite());
	launchDataPdu.getReceivingEntityID().setApplication(missile_1Espdu.getEntityID().getApplication());
	launchDataPdu.getReceivingEntityID().setEntity(missile_1Espdu.getEntityID().getEntity());

	std::vector<DIS::VariableDatum> allDatums = launchDataPdu.getVariableDatums();

	// mode=2 in datumID 0 means this is a launch data setDataPdu
	DIS::VariableDatum d0;
    d0.setVariableDatumID(0);
    d0.setVariableDatumLength(16); // in bits
    DIS::DataStream ds0(DIS::BIG);
    ds0 << (short)2;
    d0.setVariableDatums(&ds0[0], 2);
    launchDataPdu.getVariableDatums().push_back(d0);

	// Set up the target entity identifier
	DIS::VariableDatum d1;
	d1.setVariableDatumID(201);
	d1.setVariableDatumLength(48); // in bits
	DIS::DataStream ds1(DIS::BIG);
	ds1 << (short)targetEID.getSite();
	ds1 << (short)targetEID.getApplication();
	ds1 << (short)targetEID.getEntity();
	d1.setVariableDatums(&ds1[0], 6);
	launchDataPdu.getVariableDatums().push_back(d1);
}

/** creates initialization, umbilical data, and launch PDUs,
 * and sends them. The missile server should be listening for
 * these, receive them, and change its state accordingly, and
 * then start the simulation.
 */
void PluginSingleton::initializeAndLaunchMissile()
{
	SYSTEMTIME now;
	GetSystemTime(&now);

	int val = now.wSecond + now.wMinute * 60 + now.wHour * 60 * 60;

	if(val > gTimeSinceLaunch + 5)
	{
		gTimeSinceLaunch = val;
		missile_1Launched = false;
	}
	else
	{
		missile_1Launched = true;
	}

	if(missile_1Launched == true)
	{ 
		this->log("Already launched missile 1");
		return;
	}
	// For now, allow the user to repeatedly shoot the same missile
	//missile_1Launched = true;

	// Missile initialization PDU
	DIS::SetDataPdu initializationPdu;
	initializeMissile_1(initializationPdu);
	disServer->sendPdu(initializationPdu);
	
	// Umbilical data PDU, which may contain seeker information
	DIS::SetDataPdu umbilicalDataPdu;
    initializeUmbilicalDataPdu(umbilicalDataPdu);
	disServer->sendPdu(umbilicalDataPdu);

	// Launch PDU
	DIS::SetDataPdu launchDataPdu;
    initializeLaunchDataPdu(launchDataPdu);
	disServer->sendPdu(launchDataPdu);
}
