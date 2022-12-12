package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.4. abstract superclass for fire and detonation pdus that have shared information. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class WarfareFamilyPdu extends Pdu implements Serializable
{
   /** ID of the entity that shot */
   protected EntityID  firingEntityID = new EntityID(); 

   /** ID of the entity that is being shot at */
   protected EntityID  targetEntityID = new EntityID(); 


/** Constructor */
 public WarfareFamilyPdu()
 {
    setProtocolFamily( (short)2 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public WarfareFamilyPdu(edu.nps.moves.jaxb.dis.WarfareFamilyPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getFiringEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getFiringEntityID() );
     this.setFiringEntityID(foo_0);


     edu.nps.moves.dis.EntityID foo_1;
     if(x.getTargetEntityID() == null)
        foo_1 = new edu.nps.moves.dis.EntityID();
      else
        foo_1 = new edu.nps.moves.dis.EntityID(x.getTargetEntityID() );
     this.setTargetEntityID(foo_1);

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.WarfareFamilyPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.WarfareFamilyPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setFiringEntityID( this.getFiringEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setTargetEntityID( this.getTargetEntityID().initializeJaxbObject(factory.createEntityID()) );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + firingEntityID.getMarshalledSize();  // firingEntityID
   marshalSize = marshalSize + targetEntityID.getMarshalledSize();  // targetEntityID

   return marshalSize;
}


public void setFiringEntityID(EntityID pFiringEntityID)
{ firingEntityID = pFiringEntityID;
}

public EntityID getFiringEntityID()
{ return firingEntityID; }

public void setTargetEntityID(EntityID pTargetEntityID)
{ targetEntityID = pTargetEntityID;
}

public EntityID getTargetEntityID()
{ return targetEntityID; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       firingEntityID.marshal(dos);
       targetEntityID.marshal(dos);
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
       firingEntityID.unmarshal(dis);
       targetEntityID.unmarshal(dis);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(WarfareFamilyPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (firingEntityID.equals( rhs.firingEntityID) )) ivarsEqual = false;
     if( ! (targetEntityID.equals( rhs.targetEntityID) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
