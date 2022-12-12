#include "XPlanePluginHandler.h"
#include "iostream" 

#include "DIS/AcknowledgePdu.h"
#include "DIS/Pdu.h"
#include "DIS/EntityStatePdu.h"
#include "DIS/FirePdu.h"
#include "DIS/EntityManagementFamilyPdu.h"
#include "PluginSingleton.h"

/** The C++ code here is compiled by the C++ compiler. This 
 * includes the XPlanePluginHander file, which exposes some 
 * C functions. From inside these functions we can call C++
 * objects and use C++ syntax.
 *
 * @author DMcG
 */

/** 
 * Plugin start event
 */

 int wrap_startPlugin()
{
	// Get the singleton we use to hold shared, static data about the plugin state,
	// such as sockets, entity state, &c.
	PluginSingleton* singleton = PluginSingleton::getInstance();
	singleton->log("Singleton created");

	return 1;
}

 int wrap_logMessage(const char* message)
 {
	 PluginSingleton* singleton = PluginSingleton::getInstance();
	 singleton->log(message);
	 return 1;
 }

float wrap_flightLoopCallback(float                inElapsedSinceLastCall,    
                            float                inElapsedTimeSinceLastFlightLoop,    
                            int                  inCounter,    
                            void *               inRefcon)
{
	PluginSingleton* singleton = PluginSingleton::getInstance();
	return singleton->flightLoopCallback(inElapsedSinceLastCall,
		inElapsedTimeSinceLastFlightLoop,
		inCounter,
		inRefcon);
}

int wrap_MyHandleMouseClickCallback(
                                   XPLMWindowID         inWindowID,    
                                   int                  x,    
                                   int                  y,    
                                   XPLMMouseStatus      inMouse,    
                                   void *               inRefcon)
{

	PluginSingleton* singleton = PluginSingleton::getInstance();
	singleton->initializeAndLaunchMissile();

	return 1;

	// Fire the missile.
}
