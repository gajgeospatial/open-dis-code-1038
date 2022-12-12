package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * discrete ostional relationsihip 
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class NamedLocation extends Object implements Serializable
{
   /** station name enumeration */
   protected int  stationName;

   /** station number */
   protected int  stationNumber;


/** Constructor */
 public NamedLocation()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public NamedLocation(edu.nps.moves.jaxb.dis.NamedLocation x)
 {
     this.stationName = x.getStationName();
     this.stationNumber = x.getStationNumber();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.NamedLocation initializeJaxbObject(edu.nps.moves.jaxb.dis.NamedLocation x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setStationName( this.getStationName() );
     x.setStationNumber( this.getStationNumber() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // stationName
   marshalSize = marshalSize + 2;  // stationNumber

   return marshalSize;
}


public void setStationName(int pStationName)
{ stationName = pStationName;
}

public int getStationName()
{ return stationName; 
}

public void setStationNumber(int pStationNumber)
{ stationNumber = pStationNumber;
}

public int getStationNumber()
{ return stationNumber; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)stationName);
       dos.writeShort( (short)stationNumber);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       stationName = dis.readShort();
       stationNumber = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(NamedLocation rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (stationName == rhs.stationName)) ivarsEqual = false;
     if( ! (stationNumber == rhs.stationNumber)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
