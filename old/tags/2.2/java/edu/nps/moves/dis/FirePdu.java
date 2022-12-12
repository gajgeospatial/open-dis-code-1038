package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Sectioin 5.3.4.1. Information about someone firing something. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class FirePdu extends WarfareFamilyPdu implements Serializable
{
   /** ID of the munition that is being shot */
   protected EntityID  munitionID = new EntityID(); 

   /** ID of event */
   protected EventID  eventID = new EventID(); 

   protected int  fireMissionIndex;

   /** location of the firing event */
   protected Vector3Double  locationInWorldCoordinates = new Vector3Double(); 

   /** Describes munitions used in the firing event */
   protected BurstDescriptor  burstDescriptor = new BurstDescriptor(); 

   /** Velocity of the ammunition */
   protected Vector3Float  velocity = new Vector3Float(); 

   /** range to the target */
   protected float  range;


/** Constructor */
 public FirePdu()
 {
    setPduType( (short)2 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public FirePdu(edu.nps.moves.jaxb.dis.FirePdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getMunitionID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getMunitionID() );
     this.setMunitionID(foo_0);


     edu.nps.moves.dis.EventID foo_1;
     if(x.getEventID() == null)
        foo_1 = new edu.nps.moves.dis.EventID();
      else
        foo_1 = new edu.nps.moves.dis.EventID(x.getEventID() );
     this.setEventID(foo_1);

     this.fireMissionIndex = x.getFireMissionIndex();

     edu.nps.moves.dis.Vector3Double foo_3;
     if(x.getLocationInWorldCoordinates() == null)
        foo_3 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_3 = new edu.nps.moves.dis.Vector3Double(x.getLocationInWorldCoordinates() );
     this.setLocationInWorldCoordinates(foo_3);


     edu.nps.moves.dis.BurstDescriptor foo_4;
     if(x.getBurstDescriptor() == null)
        foo_4 = new edu.nps.moves.dis.BurstDescriptor();
      else
        foo_4 = new edu.nps.moves.dis.BurstDescriptor(x.getBurstDescriptor() );
     this.setBurstDescriptor(foo_4);


     edu.nps.moves.dis.Vector3Float foo_5;
     if(x.getVelocity() == null)
        foo_5 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_5 = new edu.nps.moves.dis.Vector3Float(x.getVelocity() );
     this.setVelocity(foo_5);

     this.range = x.getRange();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.FirePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.FirePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setMunitionID( this.getMunitionID().initializeJaxbObject(factory.createEntityID()) );
     x.setEventID( this.getEventID().initializeJaxbObject(factory.createEventID()) );
     x.setFireMissionIndex( this.getFireMissionIndex() );
     x.setLocationInWorldCoordinates( this.getLocationInWorldCoordinates().initializeJaxbObject(factory.createVector3Double()) );
     x.setBurstDescriptor( this.getBurstDescriptor().initializeJaxbObject(factory.createBurstDescriptor()) );
     x.setVelocity( this.getVelocity().initializeJaxbObject(factory.createVector3Float()) );
     x.setRange( this.getRange() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + munitionID.getMarshalledSize();  // munitionID
   marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
   marshalSize = marshalSize + 4;  // fireMissionIndex
   marshalSize = marshalSize + locationInWorldCoordinates.getMarshalledSize();  // locationInWorldCoordinates
   marshalSize = marshalSize + burstDescriptor.getMarshalledSize();  // burstDescriptor
   marshalSize = marshalSize + velocity.getMarshalledSize();  // velocity
   marshalSize = marshalSize + 4;  // range

   return marshalSize;
}


public void setMunitionID(EntityID pMunitionID)
{ munitionID = pMunitionID;
}

public EntityID getMunitionID()
{ return munitionID; }

public void setEventID(EventID pEventID)
{ eventID = pEventID;
}

public EventID getEventID()
{ return eventID; }

public void setFireMissionIndex(int pFireMissionIndex)
{ fireMissionIndex = pFireMissionIndex;
}

public int getFireMissionIndex()
{ return fireMissionIndex; 
}

public void setLocationInWorldCoordinates(Vector3Double pLocationInWorldCoordinates)
{ locationInWorldCoordinates = pLocationInWorldCoordinates;
}

public Vector3Double getLocationInWorldCoordinates()
{ return locationInWorldCoordinates; }

public void setBurstDescriptor(BurstDescriptor pBurstDescriptor)
{ burstDescriptor = pBurstDescriptor;
}

public BurstDescriptor getBurstDescriptor()
{ return burstDescriptor; }

public void setVelocity(Vector3Float pVelocity)
{ velocity = pVelocity;
}

public Vector3Float getVelocity()
{ return velocity; }

public void setRange(float pRange)
{ range = pRange;
}

public float getRange()
{ return range; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       munitionID.marshal(dos);
       eventID.marshal(dos);
       dos.writeInt( (int)fireMissionIndex);
       locationInWorldCoordinates.marshal(dos);
       burstDescriptor.marshal(dos);
       velocity.marshal(dos);
       dos.writeFloat( (float)range);
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
       munitionID.unmarshal(dis);
       eventID.unmarshal(dis);
       fireMissionIndex = dis.readInt();
       locationInWorldCoordinates.unmarshal(dis);
       burstDescriptor.unmarshal(dis);
       velocity.unmarshal(dis);
       range = dis.readFloat();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(FirePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (munitionID.equals( rhs.munitionID) )) ivarsEqual = false;
     if( ! (eventID.equals( rhs.eventID) )) ivarsEqual = false;
     if( ! (fireMissionIndex == rhs.fireMissionIndex)) ivarsEqual = false;
     if( ! (locationInWorldCoordinates.equals( rhs.locationInWorldCoordinates) )) ivarsEqual = false;
     if( ! (burstDescriptor.equals( rhs.burstDescriptor) )) ivarsEqual = false;
     if( ! (velocity.equals( rhs.velocity) )) ivarsEqual = false;
     if( ! (range == rhs.range)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
