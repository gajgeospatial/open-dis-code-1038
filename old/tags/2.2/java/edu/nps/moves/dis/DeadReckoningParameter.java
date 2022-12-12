package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * represents values used in dead reckoning algorithms
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class DeadReckoningParameter extends Object implements Serializable
{
   /** enumeration of what dead reckoning algorighm to use */
   protected short  deadReckoningAlgorithm;

   /** other parameters to use in the dead reckoning algorithm */
   protected byte[]  otherParameters = new byte[15]; 

   /** Linear acceleration of the entity */
   protected Vector3Float  entityLinearAcceleration = new Vector3Float(); 

   /** angular velocity of the entity */
   protected Vector3Float  entityAngularVelocity = new Vector3Float(); 


/** Constructor */
 public DeadReckoningParameter()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public DeadReckoningParameter(edu.nps.moves.jaxb.dis.DeadReckoningParameter x)
 {
     this.deadReckoningAlgorithm = x.getDeadReckoningAlgorithm();
     this.otherParameters = new byte[15];
     for(int idx = 0; idx < 15; idx++)
     {
         byte[] y = x.getOtherParameters();
         this.otherParameters[idx] = y[idx];
     }

     edu.nps.moves.dis.Vector3Float foo_2;
     if(x.getEntityLinearAcceleration() == null)
        foo_2 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_2 = new edu.nps.moves.dis.Vector3Float(x.getEntityLinearAcceleration() );
     this.setEntityLinearAcceleration(foo_2);


     edu.nps.moves.dis.Vector3Float foo_3;
     if(x.getEntityAngularVelocity() == null)
        foo_3 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_3 = new edu.nps.moves.dis.Vector3Float(x.getEntityAngularVelocity() );
     this.setEntityAngularVelocity(foo_3);

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.DeadReckoningParameter initializeJaxbObject(edu.nps.moves.jaxb.dis.DeadReckoningParameter x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setDeadReckoningAlgorithm( this.getDeadReckoningAlgorithm() );
     x.setOtherParameters( new byte[15]);
     for(int idx = 0; idx < 15; idx++)
     {
         x.getOtherParameters()[idx] = this.otherParameters[idx];
     }
     x.setEntityLinearAcceleration( this.getEntityLinearAcceleration().initializeJaxbObject(factory.createVector3Float()) );
     x.setEntityAngularVelocity( this.getEntityAngularVelocity().initializeJaxbObject(factory.createVector3Float()) );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // deadReckoningAlgorithm
   marshalSize = marshalSize + 15 * 1;  // otherParameters
   marshalSize = marshalSize + entityLinearAcceleration.getMarshalledSize();  // entityLinearAcceleration
   marshalSize = marshalSize + entityAngularVelocity.getMarshalledSize();  // entityAngularVelocity

   return marshalSize;
}


public void setDeadReckoningAlgorithm(short pDeadReckoningAlgorithm)
{ deadReckoningAlgorithm = pDeadReckoningAlgorithm;
}

public short getDeadReckoningAlgorithm()
{ return deadReckoningAlgorithm; 
}

public void setOtherParameters(byte[] pOtherParameters)
{ otherParameters = pOtherParameters;
}

public byte[] getOtherParameters()
{ return otherParameters; }

public void setEntityLinearAcceleration(Vector3Float pEntityLinearAcceleration)
{ entityLinearAcceleration = pEntityLinearAcceleration;
}

public Vector3Float getEntityLinearAcceleration()
{ return entityLinearAcceleration; }

public void setEntityAngularVelocity(Vector3Float pEntityAngularVelocity)
{ entityAngularVelocity = pEntityAngularVelocity;
}

public Vector3Float getEntityAngularVelocity()
{ return entityAngularVelocity; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)deadReckoningAlgorithm);

       for(int idx = 0; idx < otherParameters.length; idx++)
       {
           dos.writeByte(otherParameters[idx]);
       } // end of array marshaling

       entityLinearAcceleration.marshal(dos);
       entityAngularVelocity.marshal(dos);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       deadReckoningAlgorithm = dis.readByte();
       for(int idx = 0; idx < otherParameters.length; idx++)
       {
                otherParameters[idx] = dis.readByte();
       } // end of array unmarshaling
       entityLinearAcceleration.unmarshal(dis);
       entityAngularVelocity.unmarshal(dis);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(DeadReckoningParameter rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (deadReckoningAlgorithm == rhs.deadReckoningAlgorithm)) ivarsEqual = false;

     for(int idx = 0; idx < 15; idx++)
     {
          if(!(otherParameters[idx] == rhs.otherParameters[idx])) ivarsEqual = false;
     }

     if( ! (entityLinearAcceleration.equals( rhs.entityLinearAcceleration) )) ivarsEqual = false;
     if( ! (entityAngularVelocity.equals( rhs.entityAngularVelocity) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
