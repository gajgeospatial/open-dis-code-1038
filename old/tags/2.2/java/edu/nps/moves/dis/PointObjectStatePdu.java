package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.11.3: Inormation abut the addition or modification of a synthecic enviroment object that is anchored      to the terrain with a single point. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class PointObjectStatePdu extends SyntheticEnvironmentFamilyPdu implements Serializable
{
   /** Object in synthetic environment */
   protected EntityID  objectID = new EntityID(); 

   /** Object with which this point object is associated */
   protected EntityID  referencedObjectID = new EntityID(); 

   /** unique update number of each state transition of an object */
   protected int  updateNumber;

   /** force ID */
   protected short  forceID;

   /** modifications */
   protected short  modifications;

   /** Object type */
   protected ObjectType  objectType = new ObjectType(); 

   /** Object location */
   protected Vector3Double  objectLocation = new Vector3Double(); 

   /** Object orientation */
   protected Orientation  objectOrientation = new Orientation(); 

   /** Object apperance */
   protected double  objectAppearance;

   /** requesterID */
   protected SimulationAddress  requesterID = new SimulationAddress(); 

   /** receiver ID */
   protected SimulationAddress  receivingID = new SimulationAddress(); 

   /** padding */
   protected long  pad2;


/** Constructor */
 public PointObjectStatePdu()
 {
    setPduType( (short)43 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public PointObjectStatePdu(edu.nps.moves.jaxb.dis.PointObjectStatePdu x)
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

     edu.nps.moves.dis.ObjectType foo_5;
     if(x.getObjectType() == null)
        foo_5 = new edu.nps.moves.dis.ObjectType();
      else
        foo_5 = new edu.nps.moves.dis.ObjectType(x.getObjectType() );
     this.setObjectType(foo_5);


     edu.nps.moves.dis.Vector3Double foo_6;
     if(x.getObjectLocation() == null)
        foo_6 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_6 = new edu.nps.moves.dis.Vector3Double(x.getObjectLocation() );
     this.setObjectLocation(foo_6);


     edu.nps.moves.dis.Orientation foo_7;
     if(x.getObjectOrientation() == null)
        foo_7 = new edu.nps.moves.dis.Orientation();
      else
        foo_7 = new edu.nps.moves.dis.Orientation(x.getObjectOrientation() );
     this.setObjectOrientation(foo_7);

     this.objectAppearance = x.getObjectAppearance();

     edu.nps.moves.dis.SimulationAddress foo_9;
     if(x.getRequesterID() == null)
        foo_9 = new edu.nps.moves.dis.SimulationAddress();
      else
        foo_9 = new edu.nps.moves.dis.SimulationAddress(x.getRequesterID() );
     this.setRequesterID(foo_9);


     edu.nps.moves.dis.SimulationAddress foo_10;
     if(x.getReceivingID() == null)
        foo_10 = new edu.nps.moves.dis.SimulationAddress();
      else
        foo_10 = new edu.nps.moves.dis.SimulationAddress(x.getReceivingID() );
     this.setReceivingID(foo_10);

     this.pad2 = x.getPad2();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.PointObjectStatePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.PointObjectStatePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setObjectID( this.getObjectID().initializeJaxbObject(factory.createEntityID()) );
     x.setReferencedObjectID( this.getReferencedObjectID().initializeJaxbObject(factory.createEntityID()) );
     x.setUpdateNumber( this.getUpdateNumber() );
     x.setForceID( this.getForceID() );
     x.setModifications( this.getModifications() );
     x.setObjectType( this.getObjectType().initializeJaxbObject(factory.createObjectType()) );
     x.setObjectLocation( this.getObjectLocation().initializeJaxbObject(factory.createVector3Double()) );
     x.setObjectOrientation( this.getObjectOrientation().initializeJaxbObject(factory.createOrientation()) );
     x.setObjectAppearance( this.getObjectAppearance() );
     x.setRequesterID( this.getRequesterID().initializeJaxbObject(factory.createSimulationAddress()) );
     x.setReceivingID( this.getReceivingID().initializeJaxbObject(factory.createSimulationAddress()) );
     x.setPad2( this.getPad2() );
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
   marshalSize = marshalSize + objectLocation.getMarshalledSize();  // objectLocation
   marshalSize = marshalSize + objectOrientation.getMarshalledSize();  // objectOrientation
   marshalSize = marshalSize + 8;  // objectAppearance
   marshalSize = marshalSize + requesterID.getMarshalledSize();  // requesterID
   marshalSize = marshalSize + receivingID.getMarshalledSize();  // receivingID
   marshalSize = marshalSize + 4;  // pad2

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

public void setObjectType(ObjectType pObjectType)
{ objectType = pObjectType;
}

public ObjectType getObjectType()
{ return objectType; }

public void setObjectLocation(Vector3Double pObjectLocation)
{ objectLocation = pObjectLocation;
}

public Vector3Double getObjectLocation()
{ return objectLocation; }

public void setObjectOrientation(Orientation pObjectOrientation)
{ objectOrientation = pObjectOrientation;
}

public Orientation getObjectOrientation()
{ return objectOrientation; }

public void setObjectAppearance(double pObjectAppearance)
{ objectAppearance = pObjectAppearance;
}

public double getObjectAppearance()
{ return objectAppearance; 
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

public void setPad2(long pPad2)
{ pad2 = pPad2;
}

public long getPad2()
{ return pad2; 
}


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
       objectLocation.marshal(dos);
       objectOrientation.marshal(dos);
       dos.writeDouble( (double)objectAppearance);
       requesterID.marshal(dos);
       receivingID.marshal(dos);
       dos.writeInt( (int)pad2);
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
       objectLocation.unmarshal(dis);
       objectOrientation.unmarshal(dis);
       objectAppearance = dis.readDouble();
       requesterID.unmarshal(dis);
       receivingID.unmarshal(dis);
       pad2 = dis.readInt();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(PointObjectStatePdu rhs)
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
     if( ! (objectLocation.equals( rhs.objectLocation) )) ivarsEqual = false;
     if( ! (objectOrientation.equals( rhs.objectOrientation) )) ivarsEqual = false;
     if( ! (objectAppearance == rhs.objectAppearance)) ivarsEqual = false;
     if( ! (requesterID.equals( rhs.requesterID) )) ivarsEqual = false;
     if( ! (receivingID.equals( rhs.receivingID) )) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
