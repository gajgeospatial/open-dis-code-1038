#pragma once

#include "XPLMDisplay.h"
#include "XPLMGraphics.h"
#include "XPLMProcessing.h"
#include "XPLMDataAccess.h"
#include "XPLMUtilities.h"
 /** 
 * This is a conditionally compiled wrapper or bridge class between C and C++.
 * When included and subsequently compiled in a C file, it is compiled by a
 * C compiler. When included by a C++ file, it is compiled as a C++ file. 
 * This allows us to call C++ functions from a C file. 
 *
 * The C program makes a call to the exposed C function. The code then
 * turns around and calls a C++ method, using C++ syntax. It is effectively
 * an adaptor between C and C++.
 *
 * @author DMcG
 */

#ifdef __cplusplus
extern "C"
{
#endif

/** Called as plugin startup. Initializes the singleton object, starts sockets, &c. */
 int wrap_startPlugin();
 //int wrap_startPluginFunction();

 /** Logs a message to a file. */
 int wrap_logMessage(const char* message);

 /** Returns the number of seconds until the next callback is scheduled */
 float wrap_flightLoopCallback(float                inElapsedSinceLastCall,    
                               float                inElapsedTimeSinceLastFlightLoop,    
                               int                  inCounter,    
                               void *               inRefcon);

 /**
 * Wraps the mouse click handling. Returns 1 if everything OK.
 */
  int wrap_MyHandleMouseClickCallback(
                                   XPLMWindowID         inWindowID,    
                                   int                  x,    
                                   int                  y,    
                                   XPLMMouseStatus      inMouse,    
                                   void *               inRefcon);

#ifdef __cplusplus
}
#endif

