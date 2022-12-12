package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.11.5: Information about the addition/modification of an oobject that is geometrically      achored to the terrain with a set of three or more points that come to a closure. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ArealObjectStatePdu extends SyntheticEnvironmentFamilyPdu implements Serializable
{
   /** Object in synthetic environment */
   protected EntityID  objectID = new EntityID(); 

   /** Object with which this point object is associated */
   protected EntityID  referencedObjectID = new EntityID(); 

   /** unique update number of each state transition of an object */
   protected int  updateNumber;

   /** force ID */
   protected short  forceID;

   /** modifications enumeration */
   protected short  modifications;

   /** Object type */
   protected EntityType  objectType = new EntityType(); 

   /** Object appearance */
   protected SixByteChunk  objectAppearance = new SixByteChunk(); 

   /** Number of points */
   protected int  numberOfPoints;

   /** requesterID */
   protected SimulationAddress  requesterID = new SimulationAddress(); 

   /** receiver ID */
   protected SimulationAddress  receivingID = new SimulationAddress(); 

   /** location of object */
   protected List objectLocation = new ArrayList(); 

/** Constructor */
 public ArealObjectStatePdu()
 {
    setPduType( (short)45 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ArealObjectStatePdu(edu.nps.moves.jaxb.dis.ArealObjectStatePdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getObjectID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getObjectID() );
     this.setObjectID(foo_0);


     edu.nps.moves.dis.EntityID foo_1;
     if(x.getReferencedObjectID() == null)
        foo_1 = new edu.nps.moves.dis.EntityID();
      else
        foo_1 = new edu.nps.moves.dis.EntityID(x.getReferencedObjectID() );
     this.setReferencedObjectID(foo_1);

     this.updateNumber = x.getUpdateNumber();
     this.forceID = x.getForceID();
     this.modifications = x.getModifications();

     edu.nps.moves.dis.EntityType foo_5;
     if(x.getObjectType() == null)
        foo_5 = new edu.nps.moves.dis.EntityType();
      else
        foo_5 = new edu.nps.moves.dis.EntityType(x.getObjectType() );
     this.setObjectType(foo_5);


     edu.nps.moves.dis.SixByteChunk foo_6;
     if(x.getObjectAppearance() == null)
        foo_6 = new edu.nps.moves.dis.SixByteChunk();
      else
        foo_6 = new edu.nps.moves.dis.SixByteChunk(x.getObjectAppearance() );
     this.setObjectAppearance(foo_6);

     this.numberOfPoints = x.getNumberOfPoints();

     edu.nps.moves.dis.SimulationAddress foo_8;
     if(x.getRequesterID() == null)
        foo_8 = new edu.nps.moves.dis.SimulationAddress();
      else
        foo_8 = new edu.nps.moves.dis.SimulationAddress(x.getRequesterID() );
     this.setRequesterID(foo_8);


     edu.nps.moves.dis.SimulationAddress foo_9;
     if(x.getReceivingID() == null)
        foo_9 = new edu.nps.moves.dis.SimulationAddress();
      else
        foo_9 = new edu.nps.moves.dis.SimulationAddress(x.getReceivingID() );
     this.setReceivingID(foo_9);

     this.objectLocation = new ArrayList();
     for(int idx = 0; idx < x.getObjectLocation().size(); idx++)
     {
        this.objectLocation.add( new edu.nps.moves.dis.Vector3Double((edu.nps.moves.jaxb.dis.Vector3Double) x.getObjectLocation().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ArealObjectStatePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.ArealObjectStatePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setObjectID( this.getObjectID().initializeJaxbObject(factory.createEntityID()) );
     x.setReferencedObjectID( this.getReferencedObjectID().initializeJaxbObject(factory.createEntityID()) );
     x.setUpdateNumber( this.getUpdateNumber() );
     x.setForceID( this.getForceID() );
     x.setModifications( this.getModifications() );
     x.setObjectType( this.getObjectType().initializeJaxbObject(factory.createEntityType()) );
     x.setObjectAppearance( this.getObjectAppearance().initializeJaxbObject(factory.createSixByteChunk()) );
     x.setNumberOfPoints( this.getNumberOfPoints() );
     x.setRequesterID( this.getRequesterID().initializeJaxbObject(factory.createSimulationAddress()) );
     x.setReceivingID( this.getReceivingID().initializeJaxbObject(factory.createSimulationAddress()) );

     List objectLocation_1 = x.getObjectLocation();
     for(int idx = 0; idx < objectLocation.size(); idx++)
     {
         Vector3Double a = (edu.nps.moves.dis.Vector3Double)objectLocation.get(idx);
         objectLocation_1.add(a.initializeJaxbObject(factory.createVector3Double()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + objectID.getMarshalledSize();  // objectID
   marshalSize = marshalSize + referencedObjectID.getMarshalledSize();  // referencedObjectID
   marshalSize = marshalSize + 2;  // updateNumber
   marshalSize = marshalSize + 1;  // forceID
   marshalSize = marshalSize + 1;  // modifications
   marshalSize = marshalSize + objectType.getMarshalledSize();  // objectType
   marshalSize = marshalSize + objectAppearance.getMarshalledSize();  // objectAppearance
   marshalSize = marshalSize + 2;  // numberOfPoints
   marshalSize = marshalSize + requesterID.getMarshalledSize();  // requesterID
   marshalSize = marshalSize + receivingID.getMarshalledSize();  // receivingID
   for(int idx=0; idx < objectLocation.size(); idx++)
   {
        Vector3Double listElement = (Vector3Double)objectLocation.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setObjectID(EntityID pObjectID)
{ objectID = pObjectID;
}

public EntityID getObjectID()
{ return objectID; }

public void setReferencedObjectID(EntityID pReferencedObjectID)
{ referencedObjectID = pReferencedObjectID;
}

public EntityID getReferencedObjectID()
{ return referencedObjectID; }

public void setUpdateNumber(int pUpdateNumber)
{ updateNumber = pUpdateNumber;
}

public int getUpdateNumber()
{ return updateNumber; 
}

public void setForceID(short pForceID)
{ forceID = pForceID;
}

public short getForceID()
{ return forceID; 
}

public void setModifications(short pModifications)
{ modifications = pModifications;
}

public short getModifications()
{ return modifications; 
}

public void setObjectType(EntityType pObjectType)
{ objectType = pObjectType;
}

public EntityType getObjectType()
{ return objectType; }

public void setObjectAppearance(SixByteChunk pObjectAppearance)
{ objectAppearance = pObjectAppearance;
}

public SixByteChunk getObjectAppearance()
{ return objectAppearance; }

public int getNumberOfPoints()
{ return (int)objectLocation.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfPoints method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfPoints(int pNumberOfPoints)
{ numberOfPoints = pNumberOfPoints;
}

public void setRequesterID(SimulationAddress pRequesterID)
{ requesterID = pRequesterID;
}

public SimulationAddress getRequesterID()
{ return requesterID; }

public void setReceivingID(SimulationAddress pReceivingID)
{ receivingID = pReceivingID;
}

public SimulationAddress getReceivingID()
{ return receivingID; }

public void setObjectLocation(List pObjectLocation)
{ objectLocation = pObjectLocation;
}

public List getObjectLocation()
{ return objectLocation; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       objectID.marshal(dos);
       referencedObjectID.marshal(dos);
       dos.writeShort( (short)updateNumber);
       dos.writeByte( (byte)forceID);
       dos.writeByte( (byte)modifications);
       objectType.marshal(dos);
       objectAppearance.marshal(dos);
       dos.writeShort( (short)objectLocation.size());
       requesterID.marshal(dos);
       receivingID.marshal(dos);

       for(int idx = 0; idx < objectLocation.size(); idx++)
       {
            Vector3Double aVector3Double = (Vector3Double)objectLocation.get(idx);
            aVector3Double.marshal(dos);
       } // end of list marshalling

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
       objectID.unmarshal(dis);
       referencedObjectID.unmarshal(dis);
       updateNumber = dis.readShort();
       forceID = dis.readByte();
       modifications = dis.readByte();
       objectType.unmarshal(dis);
       objectAppearance.unmarshal(dis);
       numberOfPoints = dis.readShort();
       requesterID.unmarshal(dis);
       receivingID.unmarshal(dis);
        for(int idx = 0; idx < numberOfPoints; idx++)
        {
           Vector3Double anX = new Vector3Double();
            anX.unmarshal(dis);
            objectLocation.add(anX);
        };

    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(ArealObjectStatePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (objectID.equals( rhs.objectID) )) ivarsEqual = false;
     if( ! (referencedObjectID.equals( rhs.referencedObjectID) )) ivarsEqual = false;
     if( ! (updateNumber == rhs.updateNumber)) ivarsEqual = false;
     if( ! (forceID == rhs.forceID)) ivarsEqual = false;
     if( ! (modifications == rhs.modifications)) ivarsEqual = false;
     if( ! (objectType.equals( rhs.objectType) )) ivarsEqual = false;
     if( ! (objectAppearance.equals( rhs.objectAppearance) )) ivarsEqual = false;
     if( ! (numberOfPoints == rhs.numberOfPoints)) ivarsEqual = false;
     if( ! (requesterID.equals( rhs.requesterID) )) ivarsEqual = false;
     if( ! (receivingID.equals( rhs.receivingID) )) ivarsEqual = false;

     for(int idx = 0; idx < objectLocation.size(); idx++)
     {
        Vector3Double x = (Vector3Double)objectLocation.get(idx);
        if( ! ( objectLocation.get(idx).equals(rhs.objectLocation.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
