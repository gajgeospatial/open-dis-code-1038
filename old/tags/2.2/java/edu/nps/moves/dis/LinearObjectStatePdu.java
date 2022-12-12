package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.11.4: Information abut the addition or modification of a synthecic enviroment object that      is anchored to the terrain with a single point and has size or orientation. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class LinearObjectStatePdu extends SyntheticEnvironmentFamilyPdu implements Serializable
{
   /** Object in synthetic environment */
   protected EntityID  objectID = new EntityID(); 

   /** Object with which this point object is associated */
   protected EntityID  referencedObjectID = new EntityID(); 

   /** unique update number of each state transition of an object */
   protected int  updateNumber;

   /** force ID */
   protected short  forceID;

   /** number of linear segment parameters */
   protected short  numberOfSegments;

   /** requesterID */
   protected SimulationAddress  requesterID = new SimulationAddress(); 

   /** receiver ID */
   protected SimulationAddress  receivingID = new SimulationAddress(); 

   /** Object type */
   protected ObjectType  objectType = new ObjectType(); 

   /** Linear segment parameters */
   protected List linearSegmentParameters = new ArrayList(); 

/** Constructor */
 public LinearObjectStatePdu()
 {
    setPduType( (short)44 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public LinearObjectStatePdu(edu.nps.moves.jaxb.dis.LinearObjectStatePdu x)
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
     this.numberOfSegments = x.getNumberOfSegments();

     edu.nps.moves.dis.SimulationAddress foo_5;
     if(x.getRequesterID() == null)
        foo_5 = new edu.nps.moves.dis.SimulationAddress();
      else
        foo_5 = new edu.nps.moves.dis.SimulationAddress(x.getRequesterID() );
     this.setRequesterID(foo_5);


     edu.nps.moves.dis.SimulationAddress foo_6;
     if(x.getReceivingID() == null)
        foo_6 = new edu.nps.moves.dis.SimulationAddress();
      else
        foo_6 = new edu.nps.moves.dis.SimulationAddress(x.getReceivingID() );
     this.setReceivingID(foo_6);


     edu.nps.moves.dis.ObjectType foo_7;
     if(x.getObjectType() == null)
        foo_7 = new edu.nps.moves.dis.ObjectType();
      else
        foo_7 = new edu.nps.moves.dis.ObjectType(x.getObjectType() );
     this.setObjectType(foo_7);

     this.linearSegmentParameters = new ArrayList();
     for(int idx = 0; idx < x.getLinearSegmentParameters().size(); idx++)
     {
        this.linearSegmentParameters.add( new edu.nps.moves.dis.LinearSegmentParameter((edu.nps.moves.jaxb.dis.LinearSegmentParameter) x.getLinearSegmentParameters().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.LinearObjectStatePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.LinearObjectStatePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setObjectID( this.getObjectID().initializeJaxbObject(factory.createEntityID()) );
     x.setReferencedObjectID( this.getReferencedObjectID().initializeJaxbObject(factory.createEntityID()) );
     x.setUpdateNumber( this.getUpdateNumber() );
     x.setForceID( this.getForceID() );
     x.setNumberOfSegments( this.getNumberOfSegments() );
     x.setRequesterID( this.getRequesterID().initializeJaxbObject(factory.createSimulationAddress()) );
     x.setReceivingID( this.getReceivingID().initializeJaxbObject(factory.createSimulationAddress()) );
     x.setObjectType( this.getObjectType().initializeJaxbObject(factory.createObjectType()) );

     List linearSegmentParameters_1 = x.getLinearSegmentParameters();
     for(int idx = 0; idx < linearSegmentParameters.size(); idx++)
     {
         LinearSegmentParameter a = (edu.nps.moves.dis.LinearSegmentParameter)linearSegmentParameters.get(idx);
         linearSegmentParameters_1.add(a.initializeJaxbObject(factory.createLinearSegmentParameter()));
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
   marshalSize = marshalSize + 1;  // numberOfSegments
   marshalSize = marshalSize + requesterID.getMarshalledSize();  // requesterID
   marshalSize = marshalSize + receivingID.getMarshalledSize();  // receivingID
   marshalSize = marshalSize + objectType.getMarshalledSize();  // objectType
   for(int idx=0; idx < linearSegmentParameters.size(); idx++)
   {
        LinearSegmentParameter listElement = (LinearSegmentParameter)linearSegmentParameters.get(idx);
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

public short getNumberOfSegments()
{ return (short)linearSegmentParameters.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfSegments method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfSegments(short pNumberOfSegments)
{ numberOfSegments = pNumberOfSegments;
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

public void setObjectType(ObjectType pObjectType)
{ objectType = pObjectType;
}

public ObjectType getObjectType()
{ return objectType; }

public void setLinearSegmentParameters(List pLinearSegmentParameters)
{ linearSegmentParameters = pLinearSegmentParameters;
}

public List getLinearSegmentParameters()
{ return linearSegmentParameters; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       objectID.marshal(dos);
       referencedObjectID.marshal(dos);
       dos.writeShort( (short)updateNumber);
       dos.writeByte( (byte)forceID);
       dos.writeByte( (byte)linearSegmentParameters.size());
       requesterID.marshal(dos);
       receivingID.marshal(dos);
       objectType.marshal(dos);

       for(int idx = 0; idx < linearSegmentParameters.size(); idx++)
       {
            LinearSegmentParameter aLinearSegmentParameter = (LinearSegmentParameter)linearSegmentParameters.get(idx);
            aLinearSegmentParameter.marshal(dos);
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
       numberOfSegments = dis.readByte();
       requesterID.unmarshal(dis);
       receivingID.unmarshal(dis);
       objectType.unmarshal(dis);
        for(int idx = 0; idx < numberOfSegments; idx++)
        {
           LinearSegmentParameter anX = new LinearSegmentParameter();
            anX.unmarshal(dis);
            linearSegmentParameters.add(anX);
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
 public boolean equals(LinearObjectStatePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (objectID.equals( rhs.objectID) )) ivarsEqual = false;
     if( ! (referencedObjectID.equals( rhs.referencedObjectID) )) ivarsEqual = false;
     if( ! (updateNumber == rhs.updateNumber)) ivarsEqual = false;
     if( ! (forceID == rhs.forceID)) ivarsEqual = false;
     if( ! (numberOfSegments == rhs.numberOfSegments)) ivarsEqual = false;
     if( ! (requesterID.equals( rhs.requesterID) )) ivarsEqual = false;
     if( ! (receivingID.equals( rhs.receivingID) )) ivarsEqual = false;
     if( ! (objectType.equals( rhs.objectType) )) ivarsEqual = false;

     for(int idx = 0; idx < linearSegmentParameters.size(); idx++)
     {
        LinearSegmentParameter x = (LinearSegmentParameter)linearSegmentParameters.get(idx);
        if( ! ( linearSegmentParameters.get(idx).equals(rhs.linearSegmentParameters.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
