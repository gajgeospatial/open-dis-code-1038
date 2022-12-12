package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Data about a vectoring nozzle system
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class VectoringNozzleSystemData extends Object implements Serializable
{
   /** horizontal deflection angle */
   protected float  horizontalDeflectionAngle;

   /** vertical deflection angle */
   protected float  verticalDeflectionAngle;


/** Constructor */
 public VectoringNozzleSystemData()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public VectoringNozzleSystemData(edu.nps.moves.jaxb.dis.VectoringNozzleSystemData x)
 {
     this.horizontalDeflectionAngle = x.getHorizontalDeflectionAngle();
     this.verticalDeflectionAngle = x.getVerticalDeflectionAngle();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.VectoringNozzleSystemData initializeJaxbObject(edu.nps.moves.jaxb.dis.VectoringNozzleSystemData x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setHorizontalDeflectionAngle( this.getHorizontalDeflectionAngle() );
     x.setVerticalDeflectionAngle( this.getVerticalDeflectionAngle() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // horizontalDeflectionAngle
   marshalSize = marshalSize + 4;  // verticalDeflectionAngle

   return marshalSize;
}


public void setHorizontalDeflectionAngle(float pHorizontalDeflectionAngle)
{ horizontalDeflectionAngle = pHorizontalDeflectionAngle;
}

public float getHorizontalDeflectionAngle()
{ return horizontalDeflectionAngle; 
}

public void setVerticalDeflectionAngle(float pVerticalDeflectionAngle)
{ verticalDeflectionAngle = pVerticalDeflectionAngle;
}

public float getVerticalDeflectionAngle()
{ return verticalDeflectionAngle; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeFloat( (float)horizontalDeflectionAngle);
       dos.writeFloat( (float)verticalDeflectionAngle);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       horizontalDeflectionAngle = dis.readFloat();
       verticalDeflectionAngle = dis.readFloat();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(VectoringNozzleSystemData rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (horizontalDeflectionAngle == rhs.horizontalDeflectionAngle)) ivarsEqual = false;
     if( ! (verticalDeflectionAngle == rhs.verticalDeflectionAngle)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
