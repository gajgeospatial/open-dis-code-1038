package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.8. Abstract superclass for radio communications PDUs.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class RadioCommunicationsFamilyPdu extends Pdu implements Serializable
{
   /** ID of the entitythat is the source of the communication */
   protected EntityID  entityId = new EntityID(); 

   /** particular radio within an entity */
   protected int  radioId;


/** Constructor */
 public RadioCommunicationsFamilyPdu()
 {
    setProtocolFamily( (short)4 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public RadioCommunicationsFamilyPdu(edu.nps.moves.jaxb.dis.RadioCommunicationsFamilyPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getEntityId() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getEntityId() );
     this.setEntityId(foo_0);

     this.radioId = x.getRadioId();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.RadioCommunicationsFamilyPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.RadioCommunicationsFamilyPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setEntityId( this.getEntityId().initializeJaxbObject(factory.createEntityID()) );
     x.setRadioId( this.getRadioId() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + entityId.getMarshalledSize();  // entityId
   marshalSize = marshalSize + 2;  // radioId

   return marshalSize;
}


public void setEntityId(EntityID pEntityId)
{ entityId = pEntityId;
}

public EntityID getEntityId()
{ return entityId; }

public void setRadioId(int pRadioId)
{ radioId = pRadioId;
}

public int getRadioId()
{ return radioId; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       entityId.marshal(dos);
       dos.writeShort( (short)radioId);
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
       entityId.unmarshal(dis);
       radioId = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(RadioCommunicationsFamilyPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (entityId.equals( rhs.entityId) )) ivarsEqual = false;
     if( ! (radioId == rhs.radioId)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
