
package edu.nps.moves.xplane;

import java.util.HashMap;
/**
 * The various parts of the xplane UDP packet are identified by a leading
 * integer; these enumerations describe the integers. Note that different
 * versions of xplane use different numbers, so version 8, for example, won't
 * have the same numbers as version 9, for which this is written.
 * @author $Tariq Rashid
 */
public enum XPlaneEnumsVersion9 {

    FRAME_RATE(0, "Frame Rate and Time Ratio"),
    ELAPSED_TIMES(1, "Elapsed Times"),
    SPEED_VSI(3, "Speed, Vertical Speed"),
    G_LOAD(4, "Mach, G-load"),  
    ANGULAR_ACCEL(16, "Angular Acceleration"),
    ANGULAR_VELOCITY(17, "Angular Velocities"),
    ATTITUDE(18, "Pitch Roll and Heading"),
    LOCATION(20, "Lat Long Altitude"),
    VELOCITY(21, "loc, vel, dist travelled"),
    UNKNOWN(254, "unknown field type");


   /** The cardinal value of the enum, what we're usually intersted in */
    public final int value;

    /** Description of the enum */
    public final String description;
    static private HashMap<Integer, XPlaneEnumsVersion9>enumerations = new HashMap<Integer, XPlaneEnumsVersion9>();
    
    /** Augh. There should be a better way to do this. The problem is that in switch
     * statements we can't use variables, even final variables. This sets up a lookup
     * table, with the index of each array containing an enumeration instance. For
     * instance, the espdu enum is saved in slot 1, other in slot 0, etc. When we
     * read pdu type from the wire, we simply use that to index into the array and
     * find the right enumeration type. This is fast, and doesn't generate garbage,
     * as a hashtable lookup might.
     */
    
    static public XPlaneEnumsVersion9 lookup[] = new XPlaneEnumsVersion9[255];
    
    // Initialize the lookup array.
    static 
    {
        enumerations = new HashMap();
        for(XPlaneEnumsVersion9 xpe: XPlaneEnumsVersion9.values())
        {
            lookup[xpe.value] = xpe;
            enumerations.put(new Integer(xpe.value), xpe);
        }
    }
    
 
    
    /** Constructor run when each enum instance is created above */
    XPlaneEnumsVersion9(int value, String description)
    {
        this.value = value;
        this.description = description;
    }
    
    public final int getValue()
    { 
        return value;
    }
    
    public String getDescription()
    {
        return description;
    }

    /** Returns the enumerated instance with this value.
 * If there is no enumerated instance for this value, the exception is thrown.
*/
static public XPlaneEnumsVersion9 getEnumerationForValue(int aVal) throws Exception
{
    XPlaneEnumsVersion9 val;
      val = enumerations.get(new Integer(aVal));
      if(val == null)
         throw new Exception("no enumeration found for value " + aVal + " of enumeration XPlaneEnumsVersion9 ");
      return val;
}
    

    
    
}///End XPlaneEnumsVersion9

