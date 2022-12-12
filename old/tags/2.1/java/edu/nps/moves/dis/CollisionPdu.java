package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.3.2. Information about a collision. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class CollisionPdu extends EntityInformationFamilyPdu implements Serializable
{
   /** ID of the entity that issued the collision PDU */
   protected EntityID  issuingEntityID = new EntityID(); 

   /** ID of entity that has collided with the issuing entity ID */
   protected EntityID  collidingEntityID = new EntityID(); 

   /** ID of event */
   protected EventID  eventID = new EventID(); 

   /** ID of event */
   protected short  collisionType;

   /** some padding */
   protected byte  pad = 0;

   /** velocity at collision */
   protected Vector3Float  velocity = new Vector3Float(); 

   /** mass of issuing entity */
   protected float  mass;

   /** Location with respect to entity the issuing entity collided with */
   protected Vector3Float  location = new Vector3Float(); 


/** Constructor */
 public CollisionPdu()
 {
    setPduType( (short)4 );
    setProtocolFamily( (short)1 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public CollisionPdu(edu.nps.moves.jaxb.dis.CollisionPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getIssuingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getIssuingEntityID() );
     this.setIssuingEntityID(foo_0);


     edu.nps.moves.dis.EntityID foo_1;
     if(x.getCollidingEntityID() == null)
        foo_1 = new edu.nps.moves.dis.EntityID();
      else
        foo_1 = new edu.nps.moves.dis.EntityID(x.getCollidingEntityID() );
     this.setCollidingEntityID(foo_1);


     edu.nps.moves.dis.EventID foo_2;
     if(x.getEventID() == null)
        foo_2 = new edu.nps.moves.dis.EventID();
      else
        foo_2 = new edu.nps.moves.dis.EventID(x.getEventID() );
     this.setEventID(foo_2);

     this.collisionType = x.getCollisionType();
     this.pad = x.getPad();

     edu.nps.moves.dis.Vector3Float foo_5;
     if(x.getVelocity() == null)
        foo_5 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_5 = new edu.nps.moves.dis.Vector3Float(x.getVelocity() );
     this.setVelocity(foo_5);

     this.mass = x.getMass();

     edu.nps.moves.dis.Vector3Float foo_7;
     if(x.getLocation() == null)
        foo_7 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_7 = new edu.nps.moves.dis.Vector3Float(x.getLocation() );
     this.setLocation(foo_7);

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.CollisionPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.CollisionPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setIssuingEntityID( this.getIssuingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setCollidingEntityID( this.getCollidingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setEventID( this.getEventID().initializeJaxbObject(factory.createEventID()) );
     x.setCollisionType( this.getCollisionType() );
     x.setPad( this.getPad() );
     x.setVelocity( this.getVelocity().initializeJaxbObject(factory.createVector3Float()) );
     x.setMass( this.getMass() );
     x.setLocation( this.getLocation().initializeJaxbObject(factory.createVector3Float()) );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + issuingEntityID.getMarshalledSize();  // issuingEntityID
   marshalSize = marshalSize + collidingEntityID.getMarshalledSize();  // collidingEntityID
   marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
   marshalSize = marshalSize + 1;  // collisionType
   marshalSize = marshalSize + 1;  // pad
   marshalSize = marshalSize + velocity.getMarshalledSize();  // velocity
   marshalSize = marshalSize + 4;  // mass
   marshalSize = marshalSize + location.getMarshalledSize();  // location

   return marshalSize;
}


public void setIssuingEntityID(EntityID pIssuingEntityID)
{ issuingEntityID = pIssuingEntityID;
}

public EntityID getIssuingEntityID()
{ return issuingEntityID; }

public void setCollidingEntityID(EntityID pCollidingEntityID)
{ collidingEntityID = pCollidingEntityID;
}

public EntityID getCollidingEntityID()
{ return collidingEntityID; }

public void setEventID(EventID pEventID)
{ eventID = pEventID;
}

public EventID getEventID()
{ return eventID; }

public void setCollisionType(short pCollisionType)
{ collisionType = pCollisionType;
}

public short getCollisionType()
{ return collisionType; 
}

public void setPad(byte pPad)
{ pad = pPad;
}

public byte getPad()
{ return pad; 
}

public void setVelocity(Vector3Float pVelocity)
{ velocity = pVelocity;
}

public Vector3Float getVelocity()
{ return velocity; }

public void setMass(float pMass)
{ mass = pMass;
}

public float getMass()
{ return mass; 
}

public void setLocation(Vector3Float pLocation)
{ location = pLocation;
}

public Vector3Float getLocation()
{ return location; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       issuingEntityID.marshal(dos);
       collidingEntityID.marshal(dos);
       eventID.marshal(dos);
       dos.writeByte( (byte)collisionType);
       dos.writeByte( (byte)pad);
       velocity.marshal(dos);
       dos.writeFloat( (float)mass);
       location.marshal(dos);
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
       issuingEntityID.unmarshal(dis);
       collidingEntityID.unmarshal(dis);
       eventID.unmarshal(dis);
       collisionType = dis.readByte();
       pad = dis.readByte();
       velocity.unmarshal(dis);
       mass = dis.readFloat();
       location.unmarshal(dis);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(CollisionPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (issuingEntityID.equals( rhs.issuingEntityID) )) ivarsEqual = false;
     if( ! (collidingEntityID.equals( rhs.collidingEntityID) )) ivarsEqual = false;
     if( ! (eventID.equals( rhs.eventID) )) ivarsEqual = false;
     if( ! (collisionType == rhs.collisionType)) ivarsEqual = false;
     if( ! (pad == rhs.pad)) ivarsEqual = false;
     if( ! (velocity.equals( rhs.velocity) )) ivarsEqual = false;
     if( ! (mass == rhs.mass)) ivarsEqual = false;
     if( ! (location.equals( rhs.location) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
