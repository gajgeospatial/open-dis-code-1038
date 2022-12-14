package edu.nps.moves.disenum;

import java.util.HashMap;
import edu.nps.moves.siso.EnumNotFoundException;

/** Enumeration values for ProtocolVersion
 * The enumeration values are generated from the SISO DIS XML EBV document (R35), which was
 * obtained from http://discussions.sisostds.org/default.asp?action=10&fd=31<p>
 *
 * Note that this has two ways to look up an enumerated instance from a value: a fast
 * but brittle array lookup, and a slower and more garbage-intensive, but safer, method.
 * if you want to minimize memory use, get rid of one or the other.<p>
 *
 * Copyright 2008-2009. This work is licensed under the BSD license, available at
 * http://www.movesinstitute.org/licenses<p>
 *
 * @author DMcG, Jason Nelson
 */

public enum ProtocolVersion 
{

    OTHER(0, "Other"),
    DIS_PDU_VERSION_10_MAY_92(1, "DIS PDU version 1.0 (May 92)"),
    IEEE_1278_1993(2, "IEEE 1278-1993"),
    DIS_PDU_VERSION_20_THIRD_DRAFT_MAY_93(3, "DIS PDU version 2.0 - third draft (May 93)"),
    DIS_PDU_VERSION_20_FOURTH_DRAFT_REVISED_MARCH_16_1994(4, "DIS PDU version 2.0 - fourth draft (revised) March 16, 1994"),
    IEEE_12781_1995(5, "IEEE 1278.1-1995"),
    IEEE_12781A_1998(6, "IEEE 1278.1A-1998");

    /** The enumerated value */
    public final int value;

    /** Text/english description of the enumerated value */
    public final String description;

/** This is an array, with each slot corresponding to an enumerated value. This is a fast but brittle way to look up
 * enumerated values. If there is no enumeration corresponding to the value it will fail, and it will also fail if the
 * index it out of range of the array. But it is fast, and generates less garbage than the alternative of using 
 * getEnumerationForValue(). It should be used only in real-time environments, and be careful even then.<p>
 * Use as ProtocolVersion.lookup[aVal] to get the enumeration that corresponds to a value.<p>
 * In non-realtime environments, the prefered method is getEnumerationForValue().
 */
static public ProtocolVersion lookup[] = new ProtocolVersion[7];

static private HashMap<Integer, ProtocolVersion>enumerations = new HashMap<Integer, ProtocolVersion>();

/* initialize the array and hash table at class load time */
static 
{
    for(ProtocolVersion anEnum:ProtocolVersion.values())
    {
        lookup[anEnum.value] = anEnum;
        enumerations.put(new Integer(anEnum.getValue()), anEnum);
    }
}

/** Constructor */
ProtocolVersion(int value, String description)
{
    this.value = value;
    this.description = description;
}

/** Returns the string description associated with the enumerated instance with this value. 
 * If there is no enumerated instance for this value, the string Invalid enumeration: <val> is returned.
*/
static public String getDescriptionForValue(int aVal)
{
  String desc;

    ProtocolVersion val = enumerations.get(new Integer(aVal));
      if(val == null)
        desc = "Invalid enumeration: " + (new Integer(aVal)).toString();
      else
         desc = val.getDescription();

      return desc;
}

/** Returns the enumerated instance with this value. 
 * If there is no enumerated instance for this value, the exception is thrown.
*/
static public ProtocolVersion getEnumerationForValue(int aVal) throws EnumNotFoundException
{
    ProtocolVersion val;
      val = enumerations.get(new Integer(aVal));
      if(val == null)
         throw new EnumNotFoundException("no enumeration found for value " + aVal + " of enumeration ProtocolVersion");
      return val;
}

/** Returns true if there is an enumerated instance for this value, false otherwise. 
*/
static public boolean enumerationForValueExists(int aVal)
{
    ProtocolVersion val;
      val = enumerations.get(new Integer(aVal));
      if(val == null)
         return false;
      return true;
}

/** Returns the enumerated value for this enumeration */
public int getValue()
{
  return value;
}


/** Returns a text descriptioni for this enumerated value. This is usually used as the basis for the enumeration name. */
public String getDescription()
{
  return description;
}

}
