package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.4.3. Used when the antenna pattern type in the transmitter pdu is of value 2.         Specified the direction and radiation pattern from a radio transmitter's antenna.        NOTE: this class must be hand-coded to clean up some implementation details.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SphericalHarmonicAntennaPattern extends Object implements Serializable
{
   protected byte  order;


/** Constructor */
 public SphericalHarmonicAntennaPattern()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SphericalHarmonicAntennaPattern(edu.nps.moves.jaxb.dis.SphericalHarmonicAntennaPattern x)
 {
     this.order = x.getOrder();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SphericalHarmonicAntennaPattern initializeJaxbObject(edu.nps.moves.jaxb.dis.SphericalHarmonicAntennaPattern x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setOrder( this.getOrder() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // order

   return marshalSize;
}


public void setOrder(byte pOrder)
{ order = pOrder;
}

public byte getOrder()
{ return order; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)order);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       order = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(SphericalHarmonicAntennaPattern rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (order == rhs.order)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
