/*
 * HellWorld.c
 * 
 * This is a program that modifies the existing HelloWorld plugin example to use
 * DIS.
 * 
 */

#include <stdio.h>
#include <string.h>
#include "XPLMDisplay.h"
#include "XPLMGraphics.h"
#include "XPLMProcessing.h"
#include "XPLMDataAccess.h"
#include "XPLMUtilities.h"

#include "XPlanePluginHandler.h" // C/C++ bridge

// $(OutDir)\$(ProjectName).dll
/*
 * Global Variables.  We will store our single window globally.  We also record
 * whether the mouse is down from our mouse handler.  The drawing handler looks
 * at this information and draws the appropriate display.
 * 
 */

XPLMWindowID	gWindow = NULL;
int				gClicked = 0;
int             gMissileFired = 0;  // has the missile been fired? 

void MyDrawWindowCallback(
                                   XPLMWindowID         inWindowID,    
                                   void *               inRefcon);    

void MyHandleKeyCallback(
                                   XPLMWindowID         inWindowID,    
                                   char                 inKey,    
                                   XPLMKeyFlags         inFlags,    
                                   char                 inVirtualKey,    
                                   void *               inRefcon,    
                                   int                  losingFocus);    

int MyHandleMouseClickCallback(
                                   XPLMWindowID         inWindowID,    
                                   int                  x,    
                                   int                  y,    
                                   XPLMMouseStatus      inMouse,    
                                   void *               inRefcon);  

float	MyFlightLoopCallback(
                                   float                inElapsedSinceLastCall,    
                                   float                inElapsedTimeSinceLastFlightLoop,    
                                   int                  inCounter,    
                                   void *               inRefcon);    

/*
extern "C" int cppPluginStart()
{
	int result = DisPluginHandler::startPlugin();
	printf("result of call to pluginStart is %i ", result);
}
*/
/*
 * XPluginStart
 * 
 * Our start routine registers our window and does any other initialization we 
 * must do.
 * 
 */
PLUGIN_API int XPluginStart(
						char *		outName,
						char *		outSig,
						char *		outDesc)
{
	/* First we must fill in the passed in buffers to describe our
	 * plugin to the plugin-system. */

	strcpy(outName, "DisPlugin");
	strcpy(outSig, "edu.nps.moves.xplane");
	strcpy(outDesc, "A plugin that sends DIS to reflect aircraft position");

	

	/* Now we create a window.  We pass in a rectangle in left, top,
	 * right, bottom screen coordinates.  We pass in three callbacks. */

	gWindow = XPLMCreateWindow(
					50, 600, 300, 200,			/* Area of the window. */
					1,							/* Start visible. */
					MyDrawWindowCallback,		/* Callbacks */
					MyHandleKeyCallback,
					MyHandleMouseClickCallback,
					NULL);						/* Refcon - not used. */

	// Register a callback that will be executed every N seconds

	//gPlaneLat = XPLMFindDataRef("sim/flightmodel/position/latitude");
	//gPlaneLon = XPLMFindDataRef("sim/flightmodel/position/longitude");
	//gPlaneEl = XPLMFindDataRef("sim/flightmodel/position/elevation");

	XPLMRegisterFlightLoopCallback(
		MyFlightLoopCallback, // Function to call
		0.1,                  // Time interval
		NULL);                // refcon not used



	// Call C++ capable functions
	return wrap_startPlugin();

	/* We must return 1 to indicate successful initialization, otherwise we
	 * will not be called back again. */
	 
	//return 1;
}

/*
 * XPluginStop
 * 
 * Our cleanup routine deallocates our window.
 * 
 */
PLUGIN_API void	XPluginStop(void)
{
	//std::cout << "Stopping DIS plugin" << std::endl;
	XPLMDestroyWindow(gWindow);
}

/*
 * XPluginDisable
 * 
 * We do not need to do anything when we are disabled, but we must provide the handler.
 * 
 */
PLUGIN_API void XPluginDisable(void)
{
	//std::cout << "plugin disable called, doing nothing in response" << std::endl;
}

/*
 * XPluginEnable.
 * 
 * We don't do any enable-specific initialization, but we must return 1 to indicate
 * that we may be enabled at this time.
 * 
 */
PLUGIN_API int XPluginEnable(void)
{
	//std::cout << "plugin enable called, returingin 1 " << std::endl;
	return 1;
}

/*
 * XPluginReceiveMessage
 * 
 * We don't have to do anything in our receive message handler, but we must provide one.
 * 
 */
PLUGIN_API void XPluginReceiveMessage(
					XPLMPluginID	inFromWho,
					long			inMessage,
					void *			inParam)
{
	//std::cout << "plugin receive message called, doing nothing" << std::endl;
}

/*
 * MyDrawingWindowCallback
 * 
 * This callback does the work of drawing our window once per sim cycle each time
 * it is needed.  It dynamically changes the text depending on the saved mouse
 * status.  Note that we don't have to tell X-Plane to redraw us when our text
 * changes; we are redrawn by the sim continuously.
 * 
 */
void MyDrawWindowCallback(
                                   XPLMWindowID         inWindowID,    
                                   void *               inRefcon)
{
	int		left, top, right, bottom;
	float	color[] = { 1.0, 1.0, 1.0 }; 	/* RGB White */

	//std::cout << " window draw callback called" << std::endl;
	
	/* First we get the location of the window passed in to us. */
	XPLMGetWindowGeometry(inWindowID, &left, &top, &right, &bottom);
	
	/* We now use an XPLMGraphics routine to draw a translucent dark
	 * rectangle that is our window's shape. */
	XPLMDrawTranslucentDarkBox(left, top, right, bottom);

	/* Finally we draw the text into the window, also using XPLMGraphics
	 * routines.  The NULL indicates no word wrapping. */
	XPLMDrawString(color, left + 5, top - 20, 
		(char*)(gMissileFired ? "Missile Fired" : "Click Window to Fire Missile" ), NULL, xplmFont_Basic);
		
}                                   

/*
 * MyHandleKeyCallback
 * 
 * Our key handling callback does nothing in this plugin.  This is ok; 
 * we simply don't use keyboard input.
 * 
 */
void MyHandleKeyCallback(
                                   XPLMWindowID         inWindowID,    
                                   char                 inKey,    
                                   XPLMKeyFlags         inFlags,    
                                   char                 inVirtualKey,    
                                   void *               inRefcon,    
                                   int                  losingFocus)
{
}                                   

/*
 * MyHandleMouseClickCallback
 * 
 * Our mouse click callback toggles the status of our mouse variable 
 * as the mouse is clicked.  We then update our text on the next sim 
 * cycle.
 * 
 */
int MyHandleMouseClickCallback(
                                   XPLMWindowID         inWindowID,    
                                   int                  x,    
                                   int                  y,    
                                   XPLMMouseStatus      inMouse,    
                                   void *               inRefcon)
{
	if(inMouse == xplm_MouseDown)
	{
		gMissileFired = 1;
	}

	/* If we get a down or up, toggle our status click.  We will
	 * never get a down without an up if we accept the down. */
	if ((inMouse == xplm_MouseDown) || (inMouse == xplm_MouseUp))
	{
		gClicked = 1 - gClicked;
	}

	wrap_MyHandleMouseClickCallback( inWindowID, x, y, inMouse, inRefcon);
	
	/* Returning 1 tells X-Plane that we 'accepted' the click; otherwise
	 * it would be passed to the next window behind us.  If we accept
	 * the click we get mouse moved and mouse up callbacks, if we don't
	 * we do not get any more callbacks.  It is worth noting that we 
	 * will receive mouse moved and mouse up even if the mouse is dragged
	 * out of our window's box as long as the click started in our window's 
	 * box. */
	return 1;
}    


/** Initially scheduled at startup, this callback schedules itself to run again
 * at some time interval, perhaps once every tenth of a second. You can also 
 * schedule it to run at the end or beginning of every simulation loop.
 */
float	MyFlightLoopCallback(
                                   float                inElapsedSinceLastCall,    
                                   float                inElapsedTimeSinceLastFlightLoop,    
                                   int                  inCounter,    
                                   void *               inRefcon)
{

	return wrap_flightLoopCallback(inElapsedSinceLastCall,inElapsedTimeSinceLastFlightLoop,
         inCounter,inRefcon);
	/*
	float	elapsed ;
	double	lat;
	double	lon;
	double	el;
	char buf[200];
	*/
	
	/* The actual callback.  First we read the sim's time and the data. */
	/*
		elapsed = XPLMGetElapsedTime();
		lat = XPLMGetDatad(gPlaneLat);
		lon = XPLMGetDatad(gPlaneLon);
		el = XPLMGetDatad(gPlaneEl);

		sprintf(buf, "Since last call: %g Time: %g Lat:%g Lon:%g Elevation:%g",inElapsedSinceLastCall,elapsed, lat, lon, el);
	wrap_logMessage(buf);

	*/

	
	/* Return 1.0 to indicate that we want to be called again in 1 second. */
	//return 0.1;
}                    

