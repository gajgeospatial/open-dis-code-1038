package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.12: Abstract superclass for reliable simulation management PDUs
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SimulationManagementWithReliabilityFamilyPdu extends Pdu implements Serializable
{
   /** Object originatig the request */
   protected EntityID  originatingEntityID = new EntityID(); 

   /** Object with which this point object is associated */
   protected EntityID  receivingEntityID = new EntityID(); 


/** Constructor */
 public SimulationManagementWithReliabilityFamilyPdu()
 {
    setProtocolFamily( (short)10 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SimulationManagementWithReliabilityFamilyPdu(edu.nps.moves.jaxb.dis.SimulationManagementWithReliabilityFamilyPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getOriginatingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getOriginatingEntityID() );
     this.setOriginatingEntityID(foo_0);


     edu.nps.moves.dis.EntityID foo_1;
     if(x.getReceivingEntityID() == null)
        foo_1 = new edu.nps.moves.dis.EntityID();
      else
        foo_1 = new edu.nps.moves.dis.EntityID(x.getReceivingEntityID() );
     this.setReceivingEntityID(foo_1);

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SimulationManagementWithReliabilityFamilyPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.SimulationManagementWithReliabilityFamilyPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setOriginatingEntityID( this.getOriginatingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setReceivingEntityID( this.getReceivingEntityID().initializeJaxbObject(factory.createEntityID()) );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + originatingEntityID.getMarshalledSize();  // originatingEntityID
   marshalSize = marshalSize + receivingEntityID.getMarshalledSize();  // receivingEntityID

   return marshalSize;
}


public void setOriginatingEntityID(EntityID pOriginatingEntityID)
{ originatingEntityID = pOriginatingEntityID;
}

public EntityID getOriginatingEntityID()
{ return originatingEntityID; }

public void setReceivingEntityID(EntityID pReceivingEntityID)
{ receivingEntityID = pReceivingEntityID;
}

public EntityID getReceivingEntityID()
{ return receivingEntityID; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       originatingEntityID.marshal(dos);
       receivingEntityID.marshal(dos);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    super.unmarshal(dis);

    try 
    {
       originatingEntityID.unmarshal(dis);
       receivingEntityID.unmarshal(dis);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(SimulationManagementWithReliabilityFamilyPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (originatingEntityID.equals( rhs.originatingEntityID) )) ivarsEqual = false;
     if( ! (receivingEntityID.equals( rhs.receivingEntityID) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
