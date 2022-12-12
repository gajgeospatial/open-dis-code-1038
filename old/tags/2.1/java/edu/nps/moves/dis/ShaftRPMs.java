package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Shaft RPMs, used in underwater acoustic clacluations.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ShaftRPMs extends Object implements Serializable
{
   /** Current shaft RPMs */
   protected short  currentShaftRPMs;

   /** ordered shaft rpms */
   protected short  orderedShaftRPMs;

   /** rate of change of shaft RPMs */
   protected float  shaftRPMRateOfChange;


/** Constructor */
 public ShaftRPMs()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ShaftRPMs(edu.nps.moves.jaxb.dis.ShaftRPMs x)
 {
     this.currentShaftRPMs = x.getCurrentShaftRPMs();
     this.orderedShaftRPMs = x.getOrderedShaftRPMs();
     this.shaftRPMRateOfChange = x.getShaftRPMRateOfChange();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ShaftRPMs initializeJaxbObject(edu.nps.moves.jaxb.dis.ShaftRPMs x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setCurrentShaftRPMs( this.getCurrentShaftRPMs() );
     x.setOrderedShaftRPMs( this.getOrderedShaftRPMs() );
     x.setShaftRPMRateOfChange( this.getShaftRPMRateOfChange() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // currentShaftRPMs
   marshalSize = marshalSize + 2;  // orderedShaftRPMs
   marshalSize = marshalSize + 4;  // shaftRPMRateOfChange

   return marshalSize;
}


public void setCurrentShaftRPMs(short pCurrentShaftRPMs)
{ currentShaftRPMs = pCurrentShaftRPMs;
}

public short getCurrentShaftRPMs()
{ return currentShaftRPMs; 
}

public void setOrderedShaftRPMs(short pOrderedShaftRPMs)
{ orderedShaftRPMs = pOrderedShaftRPMs;
}

public short getOrderedShaftRPMs()
{ return orderedShaftRPMs; 
}

public void setShaftRPMRateOfChange(float pShaftRPMRateOfChange)
{ shaftRPMRateOfChange = pShaftRPMRateOfChange;
}

public float getShaftRPMRateOfChange()
{ return shaftRPMRateOfChange; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)currentShaftRPMs);
       dos.writeShort( (short)orderedShaftRPMs);
       dos.writeFloat( (float)shaftRPMRateOfChange);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       currentShaftRPMs = dis.readShort();
       orderedShaftRPMs = dis.readShort();
       shaftRPMRateOfChange = dis.readFloat();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(ShaftRPMs rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (currentShaftRPMs == rhs.currentShaftRPMs)) ivarsEqual = false;
     if( ! (orderedShaftRPMs == rhs.orderedShaftRPMs)) ivarsEqual = false;
     if( ! (shaftRPMRateOfChange == rhs.shaftRPMRateOfChange)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
