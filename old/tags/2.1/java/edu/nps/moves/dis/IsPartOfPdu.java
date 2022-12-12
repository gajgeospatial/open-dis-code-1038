package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.9.4 The joining of two or more simulation entities is communicated by this PDU. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IsPartOfPdu extends EntityManagementFamilyPdu implements Serializable
{
   /** ID of entity originating PDU */
   protected EntityID  orginatingEntityID = new EntityID(); 

   /** ID of entity receiving PDU */
   protected EntityID  receivingEntityID = new EntityID(); 

   /** relationship of joined parts */
   protected Relationship  relationship = new Relationship(); 

   /** location of part; centroid of part in host's coordinate system. x=range, y=bearing, z=0 */
   protected Vector3Float  partLocation = new Vector3Float(); 

   /** named location */
   protected NamedLocation  namedLocationID = new NamedLocation(); 

   /** entity type */
   protected EntityType  partEntityType = new EntityType(); 


/** Constructor */
 public IsPartOfPdu()
 {
    setPduType( (short)36 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public IsPartOfPdu(edu.nps.moves.jaxb.dis.IsPartOfPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getOrginatingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getOrginatingEntityID() );
     this.setOrginatingEntityID(foo_0);


     edu.nps.moves.dis.EntityID foo_1;
     if(x.getReceivingEntityID() == null)
        foo_1 = new edu.nps.moves.dis.EntityID();
      else
        foo_1 = new edu.nps.moves.dis.EntityID(x.getReceivingEntityID() );
     this.setReceivingEntityID(foo_1);


     edu.nps.moves.dis.Relationship foo_2;
     if(x.getRelationship() == null)
        foo_2 = new edu.nps.moves.dis.Relationship();
      else
        foo_2 = new edu.nps.moves.dis.Relationship(x.getRelationship() );
     this.setRelationship(foo_2);


     edu.nps.moves.dis.Vector3Float foo_3;
     if(x.getPartLocation() == null)
        foo_3 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_3 = new edu.nps.moves.dis.Vector3Float(x.getPartLocation() );
     this.setPartLocation(foo_3);


     edu.nps.moves.dis.NamedLocation foo_4;
     if(x.getNamedLocationID() == null)
        foo_4 = new edu.nps.moves.dis.NamedLocation();
      else
        foo_4 = new edu.nps.moves.dis.NamedLocation(x.getNamedLocationID() );
     this.setNamedLocationID(foo_4);


     edu.nps.moves.dis.EntityType foo_5;
     if(x.getPartEntityType() == null)
        foo_5 = new edu.nps.moves.dis.EntityType();
      else
        foo_5 = new edu.nps.moves.dis.EntityType(x.getPartEntityType() );
     this.setPartEntityType(foo_5);

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.IsPartOfPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.IsPartOfPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setOrginatingEntityID( this.getOrginatingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setReceivingEntityID( this.getReceivingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setRelationship( this.getRelationship().initializeJaxbObject(factory.createRelationship()) );
     x.setPartLocation( this.getPartLocation().initializeJaxbObject(factory.createVector3Float()) );
     x.setNamedLocationID( this.getNamedLocationID().initializeJaxbObject(factory.createNamedLocation()) );
     x.setPartEntityType( this.getPartEntityType().initializeJaxbObject(factory.createEntityType()) );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + orginatingEntityID.getMarshalledSize();  // orginatingEntityID
   marshalSize = marshalSize + receivingEntityID.getMarshalledSize();  // receivingEntityID
   marshalSize = marshalSize + relationship.getMarshalledSize();  // relationship
   marshalSize = marshalSize + partLocation.getMarshalledSize();  // partLocation
   marshalSize = marshalSize + namedLocationID.getMarshalledSize();  // namedLocationID
   marshalSize = marshalSize + partEntityType.getMarshalledSize();  // partEntityType

   return marshalSize;
}


public void setOrginatingEntityID(EntityID pOrginatingEntityID)
{ orginatingEntityID = pOrginatingEntityID;
}

public EntityID getOrginatingEntityID()
{ return orginatingEntityID; }

public void setReceivingEntityID(EntityID pReceivingEntityID)
{ receivingEntityID = pReceivingEntityID;
}

public EntityID getReceivingEntityID()
{ return receivingEntityID; }

public void setRelationship(Relationship pRelationship)
{ relationship = pRelationship;
}

public Relationship getRelationship()
{ return relationship; }

public void setPartLocation(Vector3Float pPartLocation)
{ partLocation = pPartLocation;
}

public Vector3Float getPartLocation()
{ return partLocation; }

public void setNamedLocationID(NamedLocation pNamedLocationID)
{ namedLocationID = pNamedLocationID;
}

public NamedLocation getNamedLocationID()
{ return namedLocationID; }

public void setPartEntityType(EntityType pPartEntityType)
{ partEntityType = pPartEntityType;
}

public EntityType getPartEntityType()
{ return partEntityType; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       orginatingEntityID.marshal(dos);
       receivingEntityID.marshal(dos);
       relationship.marshal(dos);
       partLocation.marshal(dos);
       namedLocationID.marshal(dos);
       partEntityType.marshal(dos);
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
       orginatingEntityID.unmarshal(dis);
       receivingEntityID.unmarshal(dis);
       relationship.unmarshal(dis);
       partLocation.unmarshal(dis);
       namedLocationID.unmarshal(dis);
       partEntityType.unmarshal(dis);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(IsPartOfPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (orginatingEntityID.equals( rhs.orginatingEntityID) )) ivarsEqual = false;
     if( ! (receivingEntityID.equals( rhs.receivingEntityID) )) ivarsEqual = false;
     if( ! (relationship.equals( rhs.relationship) )) ivarsEqual = false;
     if( ! (partLocation.equals( rhs.partLocation) )) ivarsEqual = false;
     if( ! (namedLocationID.equals( rhs.namedLocationID) )) ivarsEqual = false;
     if( ! (partEntityType.equals( rhs.partEntityType) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
