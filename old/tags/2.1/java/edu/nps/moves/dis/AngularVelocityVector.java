package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.2: angular velocity measured in radians per second out each of the entity's own coordinate axes.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class AngularVelocityVector extends Object implements Serializable
{
   /** velocity about the x axis */
   protected float  x = 0;

   /** velocity about the y axis */
   protected float  y = 0;

   /** velocity about the zaxis */
   protected float  z = 0;


/** Constructor */
 public AngularVelocityVector()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public AngularVelocityVector(edu.nps.moves.jaxb.dis.AngularVelocityVector x)
 {
     this.x = x.getX();
     this.y = x.getY();
     this.z = x.getZ();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.AngularVelocityVector initializeJaxbObject(edu.nps.moves.jaxb.dis.AngularVelocityVector x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setX( this.getX() );
     x.setY( this.getY() );
     x.setZ( this.getZ() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // x
   marshalSize = marshalSize + 4;  // y
   marshalSize = marshalSize + 4;  // z

   return marshalSize;
}


public void setX(float pX)
{ x = pX;
}

public float getX()
{ return x; 
}

public void setY(float pY)
{ y = pY;
}

public float getY()
{ return y; 
}

public void setZ(float pZ)
{ z = pZ;
}

public float getZ()
{ return z; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeFloat( (float)x);
       dos.writeFloat( (float)y);
       dos.writeFloat( (float)z);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       x = dis.readFloat();
       y = dis.readFloat();
       z = dis.readFloat();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(AngularVelocityVector rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (x == rhs.x)) ivarsEqual = false;
     if( ! (y == rhs.y)) ivarsEqual = false;
     if( ! (z == rhs.z)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
