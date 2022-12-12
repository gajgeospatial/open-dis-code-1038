package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.17. Three floating point values representing an orientation, psi, theta, and phi, aka the euler angles, in radians
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class Orientation extends Object implements Serializable
{
   protected float  psi;

   protected float  theta;

   protected float  phi;


/** Constructor */
 public Orientation()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public Orientation(edu.nps.moves.jaxb.dis.Orientation x)
 {
     this.psi = x.getPsi();
     this.theta = x.getTheta();
     this.phi = x.getPhi();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.Orientation initializeJaxbObject(edu.nps.moves.jaxb.dis.Orientation x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setPsi( this.getPsi() );
     x.setTheta( this.getTheta() );
     x.setPhi( this.getPhi() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // psi
   marshalSize = marshalSize + 4;  // theta
   marshalSize = marshalSize + 4;  // phi

   return marshalSize;
}


public void setPsi(float pPsi)
{ psi = pPsi;
}

public float getPsi()
{ return psi; 
}

public void setTheta(float pTheta)
{ theta = pTheta;
}

public float getTheta()
{ return theta; 
}

public void setPhi(float pPhi)
{ phi = pPhi;
}

public float getPhi()
{ return phi; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeFloat( (float)psi);
       dos.writeFloat( (float)theta);
       dos.writeFloat( (float)phi);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       psi = dis.readFloat();
       theta = dis.readFloat();
       phi = dis.readFloat();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(Orientation rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (psi == rhs.psi)) ivarsEqual = false;
     if( ! (theta == rhs.theta)) ivarsEqual = false;
     if( ! (phi == rhs.phi)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
