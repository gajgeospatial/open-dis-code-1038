package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.3.1. Represents the postion and state of one entity in the world. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class EntityStatePdu extends EntityInformationFamilyPdu implements Serializable
{
   /** Unique ID for an entity that is tied to this state information */
   protected EntityID  entityID = new EntityID(); 

   /** What force this entity is affiliated with, eg red, blue, neutral, etc */
   protected short  forceId;

   /** How many articulation parameters are in the variable length list */
   protected byte  numberOfArticulationParameters;

   /** Describes the type of entity in the world */
   protected EntityType  entityType = new EntityType(); 

   protected EntityType  alternativeEntityType = new EntityType(); 

   /** Describes the speed of the entity in the world */
   protected Vector3Float  entityLinearVelocity = new Vector3Float(); 

   /** describes the location of the entity in the world */
   protected Vector3Double  entityLocation = new Vector3Double(); 

   /** describes the orientation of the entity, in euler angles */
   protected Orientation  entityOrientation = new Orientation(); 

   /** a series of bit flags that are used to help draw the entity, such as smoking, on fire, etc. */
   protected int  entityAppearance;

   /** parameters used for dead reckoning */
   protected DeadReckoningParameter  deadReckoningParameters = new DeadReckoningParameter(); 

   /** characters that can be used for debugging, or to draw unique strings on the side of entities in the world */
   protected Marking  marking = new Marking(); 

   /** a series of bit flags */
   protected int  capabilities;

   /** variable length list of articulation parameters */
   protected List articulationParameters = new ArrayList(); 

/** Constructor */
 public EntityStatePdu()
 {
    setPduType( (short)1 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public EntityStatePdu(edu.nps.moves.jaxb.dis.EntityStatePdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getEntityID() );
     this.setEntityID(foo_0);

     this.forceId = x.getForceId();
     this.numberOfArticulationParameters = x.getNumberOfArticulationParameters();

     edu.nps.moves.dis.EntityType foo_3;
     if(x.getEntityType() == null)
        foo_3 = new edu.nps.moves.dis.EntityType();
      else
        foo_3 = new edu.nps.moves.dis.EntityType(x.getEntityType() );
     this.setEntityType(foo_3);


     edu.nps.moves.dis.EntityType foo_4;
     if(x.getAlternativeEntityType() == null)
        foo_4 = new edu.nps.moves.dis.EntityType();
      else
        foo_4 = new edu.nps.moves.dis.EntityType(x.getAlternativeEntityType() );
     this.setAlternativeEntityType(foo_4);


     edu.nps.moves.dis.Vector3Float foo_5;
     if(x.getEntityLinearVelocity() == null)
        foo_5 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_5 = new edu.nps.moves.dis.Vector3Float(x.getEntityLinearVelocity() );
     this.setEntityLinearVelocity(foo_5);


     edu.nps.moves.dis.Vector3Double foo_6;
     if(x.getEntityLocation() == null)
        foo_6 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_6 = new edu.nps.moves.dis.Vector3Double(x.getEntityLocation() );
     this.setEntityLocation(foo_6);


     edu.nps.moves.dis.Orientation foo_7;
     if(x.getEntityOrientation() == null)
        foo_7 = new edu.nps.moves.dis.Orientation();
      else
        foo_7 = new edu.nps.moves.dis.Orientation(x.getEntityOrientation() );
     this.setEntityOrientation(foo_7);

     this.entityAppearance = x.getEntityAppearance();

     edu.nps.moves.dis.DeadReckoningParameter foo_9;
     if(x.getDeadReckoningParameters() == null)
        foo_9 = new edu.nps.moves.dis.DeadReckoningParameter();
      else
        foo_9 = new edu.nps.moves.dis.DeadReckoningParameter(x.getDeadReckoningParameters() );
     this.setDeadReckoningParameters(foo_9);


     edu.nps.moves.dis.Marking foo_10;
     if(x.getMarking() == null)
        foo_10 = new edu.nps.moves.dis.Marking();
      else
        foo_10 = new edu.nps.moves.dis.Marking(x.getMarking() );
     this.setMarking(foo_10);

     this.capabilities = x.getCapabilities();
     this.articulationParameters = new ArrayList();
     for(int idx = 0; idx < x.getArticulationParameters().size(); idx++)
     {
        this.articulationParameters.add( new edu.nps.moves.dis.ArticulationParameter((edu.nps.moves.jaxb.dis.ArticulationParameter) x.getArticulationParameters().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.EntityStatePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.EntityStatePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setEntityID( this.getEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setForceId( this.getForceId() );
     x.setNumberOfArticulationParameters( this.getNumberOfArticulationParameters() );
     x.setEntityType( this.getEntityType().initializeJaxbObject(factory.createEntityType()) );
     x.setAlternativeEntityType( this.getAlternativeEntityType().initializeJaxbObject(factory.createEntityType()) );
     x.setEntityLinearVelocity( this.getEntityLinearVelocity().initializeJaxbObject(factory.createVector3Float()) );
     x.setEntityLocation( this.getEntityLocation().initializeJaxbObject(factory.createVector3Double()) );
     x.setEntityOrientation( this.getEntityOrientation().initializeJaxbObject(factory.createOrientation()) );
     x.setEntityAppearance( this.getEntityAppearance() );
     x.setDeadReckoningParameters( this.getDeadReckoningParameters().initializeJaxbObject(factory.createDeadReckoningParameter()) );
     x.setMarking( this.getMarking().initializeJaxbObject(factory.createMarking()) );
     x.setCapabilities( this.getCapabilities() );

     List articulationParameters_1 = x.getArticulationParameters();
     for(int idx = 0; idx < articulationParameters.size(); idx++)
     {
         ArticulationParameter a = (edu.nps.moves.dis.ArticulationParameter)articulationParameters.get(idx);
         articulationParameters_1.add(a.initializeJaxbObject(factory.createArticulationParameter()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + entityID.getMarshalledSize();  // entityID
   marshalSize = marshalSize + 1;  // forceId
   marshalSize = marshalSize + 1;  // numberOfArticulationParameters
   marshalSize = marshalSize + entityType.getMarshalledSize();  // entityType
   marshalSize = marshalSize + alternativeEntityType.getMarshalledSize();  // alternativeEntityType
   marshalSize = marshalSize + entityLinearVelocity.getMarshalledSize();  // entityLinearVelocity
   marshalSize = marshalSize + entityLocation.getMarshalledSize();  // entityLocation
   marshalSize = marshalSize + entityOrientation.getMarshalledSize();  // entityOrientation
   marshalSize = marshalSize + 4;  // entityAppearance
   marshalSize = marshalSize + deadReckoningParameters.getMarshalledSize();  // deadReckoningParameters
   marshalSize = marshalSize + marking.getMarshalledSize();  // marking
   marshalSize = marshalSize + 4;  // capabilities
   for(int idx=0; idx < articulationParameters.size(); idx++)
   {
        ArticulationParameter listElement = (ArticulationParameter)articulationParameters.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setEntityID(EntityID pEntityID)
{ entityID = pEntityID;
}

public EntityID getEntityID()
{ return entityID; }

public void setForceId(short pForceId)
{ forceId = pForceId;
}

public short getForceId()
{ return forceId; 
}

public byte getNumberOfArticulationParameters()
{ return (byte)articulationParameters.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfArticulationParameters method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfArticulationParameters(byte pNumberOfArticulationParameters)
{ numberOfArticulationParameters = pNumberOfArticulationParameters;
}

public void setEntityType(EntityType pEntityType)
{ entityType = pEntityType;
}

public EntityType getEntityType()
{ return entityType; }

public void setAlternativeEntityType(EntityType pAlternativeEntityType)
{ alternativeEntityType = pAlternativeEntityType;
}

public EntityType getAlternativeEntityType()
{ return alternativeEntityType; }

public void setEntityLinearVelocity(Vector3Float pEntityLinearVelocity)
{ entityLinearVelocity = pEntityLinearVelocity;
}

public Vector3Float getEntityLinearVelocity()
{ return entityLinearVelocity; }

public void setEntityLocation(Vector3Double pEntityLocation)
{ entityLocation = pEntityLocation;
}

public Vector3Double getEntityLocation()
{ return entityLocation; }

public void setEntityOrientation(Orientation pEntityOrientation)
{ entityOrientation = pEntityOrientation;
}

public Orientation getEntityOrientation()
{ return entityOrientation; }

public void setEntityAppearance(int pEntityAppearance)
{ entityAppearance = pEntityAppearance;
}

public int getEntityAppearance()
{ return entityAppearance; 
}

public void setDeadReckoningParameters(DeadReckoningParameter pDeadReckoningParameters)
{ deadReckoningParameters = pDeadReckoningParameters;
}

public DeadReckoningParameter getDeadReckoningParameters()
{ return deadReckoningParameters; }

public void setMarking(Marking pMarking)
{ marking = pMarking;
}

public Marking getMarking()
{ return marking; }

public void setCapabilities(int pCapabilities)
{ capabilities = pCapabilities;
}

public int getCapabilities()
{ return capabilities; 
}

public void setArticulationParameters(List pArticulationParameters)
{ articulationParameters = pArticulationParameters;
}

public List getArticulationParameters()
{ return articulationParameters; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       entityID.marshal(dos);
       dos.writeByte( (byte)forceId);
       dos.writeByte( (byte)articulationParameters.size());
       entityType.marshal(dos);
       alternativeEntityType.marshal(dos);
       entityLinearVelocity.marshal(dos);
       entityLocation.marshal(dos);
       entityOrientation.marshal(dos);
       dos.writeInt( (int)entityAppearance);
       deadReckoningParameters.marshal(dos);
       marking.marshal(dos);
       dos.writeInt( (int)capabilities);

       for(int idx = 0; idx < articulationParameters.size(); idx++)
       {
            ArticulationParameter aArticulationParameter = (ArticulationParameter)articulationParameters.get(idx);
            aArticulationParameter.marshal(dos);
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
       entityID.unmarshal(dis);
       forceId = dis.readByte();
       numberOfArticulationParameters = dis.readByte();
       entityType.unmarshal(dis);
       alternativeEntityType.unmarshal(dis);
       entityLinearVelocity.unmarshal(dis);
       entityLocation.unmarshal(dis);
       entityOrientation.unmarshal(dis);
       entityAppearance = dis.readInt();
       deadReckoningParameters.unmarshal(dis);
       marking.unmarshal(dis);
       capabilities = dis.readInt();
        for(int idx = 0; idx < numberOfArticulationParameters; idx++)
        {
           ArticulationParameter anX = new ArticulationParameter();
            anX.unmarshal(dis);
            articulationParameters.add(anX);
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
 public boolean equals(EntityStatePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (entityID.equals( rhs.entityID) )) ivarsEqual = false;
     if( ! (forceId == rhs.forceId)) ivarsEqual = false;
     if( ! (numberOfArticulationParameters == rhs.numberOfArticulationParameters)) ivarsEqual = false;
     if( ! (entityType.equals( rhs.entityType) )) ivarsEqual = false;
     if( ! (alternativeEntityType.equals( rhs.alternativeEntityType) )) ivarsEqual = false;
     if( ! (entityLinearVelocity.equals( rhs.entityLinearVelocity) )) ivarsEqual = false;
     if( ! (entityLocation.equals( rhs.entityLocation) )) ivarsEqual = false;
     if( ! (entityOrientation.equals( rhs.entityOrientation) )) ivarsEqual = false;
     if( ! (entityAppearance == rhs.entityAppearance)) ivarsEqual = false;
     if( ! (deadReckoningParameters.equals( rhs.deadReckoningParameters) )) ivarsEqual = false;
     if( ! (marking.equals( rhs.marking) )) ivarsEqual = false;
     if( ! (capabilities == rhs.capabilities)) ivarsEqual = false;

     for(int idx = 0; idx < articulationParameters.size(); idx++)
     {
        ArticulationParameter x = (ArticulationParameter)articulationParameters.get(idx);
        if( ! ( articulationParameters.get(idx).equals(rhs.articulationParameters.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
