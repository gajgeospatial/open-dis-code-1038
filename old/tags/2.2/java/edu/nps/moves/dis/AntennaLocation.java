package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.3: location of the radiating portion of the antenna, specified in world coordinates and         entity coordinates.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class AntennaLocation extends Object implements Serializable
{
   /** Location of the radiating portion of the antenna in world    coordinates */
   protected Vector3Double  antennaLocation = new Vector3Double(); 

   /** Location of the radiating portion of the antenna     in entity coordinates */
   protected Vector3Float  relativeAntennaLocation = new Vector3Float(); 


/** Constructor */
 public AntennaLocation()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public AntennaLocation(edu.nps.moves.jaxb.dis.AntennaLocation x)
 {

     edu.nps.moves.dis.Vector3Double foo_0;
     if(x.getAntennaLocation() == null)
        foo_0 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_0 = new edu.nps.moves.dis.Vector3Double(x.getAntennaLocation() );
     this.setAntennaLocation(foo_0);


     edu.nps.moves.dis.Vector3Float foo_1;
     if(x.getRelativeAntennaLocation() == null)
        foo_1 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_1 = new edu.nps.moves.dis.Vector3Float(x.getRelativeAntennaLocation() );
     this.setRelativeAntennaLocation(foo_1);

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.AntennaLocation initializeJaxbObject(edu.nps.moves.jaxb.dis.AntennaLocation x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setAntennaLocation( this.getAntennaLocation().initializeJaxbObject(factory.createVector3Double()) );
     x.setRelativeAntennaLocation( this.getRelativeAntennaLocation().initializeJaxbObject(factory.createVector3Float()) );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + antennaLocation.getMarshalledSize();  // antennaLocation
   marshalSize = marshalSize + relativeAntennaLocation.getMarshalledSize();  // relativeAntennaLocation

   return marshalSize;
}


public void setAntennaLocation(Vector3Double pAntennaLocation)
{ antennaLocation = pAntennaLocation;
}

public Vector3Double getAntennaLocation()
{ return antennaLocation; }

public void setRelativeAntennaLocation(Vector3Float pRelativeAntennaLocation)
{ relativeAntennaLocation = pRelativeAntennaLocation;
}

public Vector3Float getRelativeAntennaLocation()
{ return relativeAntennaLocation; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       antennaLocation.marshal(dos);
       relativeAntennaLocation.marshal(dos);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       antennaLocation.unmarshal(dis);
       relativeAntennaLocation.unmarshal(dis);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(AntennaLocation rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (antennaLocation.equals( rhs.antennaLocation) )) ivarsEqual = false;
     if( ! (relativeAntennaLocation.equals( rhs.relativeAntennaLocation) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
