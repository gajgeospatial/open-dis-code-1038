package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.3.3.4. Nonstatic information about a particular entity may be communicated by issuing an Entity State Update PDU. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class EntityStateUpdatePdu extends EntityInformationFamilyPdu implements Serializable
{
   /** This field shall identify the entity issuing the PDU */
   protected EntityID  entityID = new EntityID(); 

   /** How many articulation parameters are in the variable length list */
   protected byte  numberOfArticulationParameters;

   /** Describes the speed of the entity in the world */
   protected Vector3Float  entityLinearVelocity = new Vector3Float(); 

   /** describes the location of the entity in the world */
   protected Vector3Double  entityLocation = new Vector3Double(); 

   /** describes the orientation of the entity, in euler angles */
   protected Orientation  entityOrientation = new Orientation(); 

   /** a series of bit flags that are used to help draw the entity, such as smoking, on fire, etc. */
   protected int  entityAppearance;

   protected List articulationParameters = new ArrayList(); 

/** Constructor */
 public EntityStateUpdatePdu()
 {
    setPduType( (short)67 );
    setProtocolFamily( (short)1 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public EntityStateUpdatePdu(edu.nps.moves.jaxb.dis.EntityStateUpdatePdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getEntityID() );
     this.setEntityID(foo_0);

     this.numberOfArticulationParameters = x.getNumberOfArticulationParameters();

     edu.nps.moves.dis.Vector3Float foo_2;
     if(x.getEntityLinearVelocity() == null)
        foo_2 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_2 = new edu.nps.moves.dis.Vector3Float(x.getEntityLinearVelocity() );
     this.setEntityLinearVelocity(foo_2);


     edu.nps.moves.dis.Vector3Double foo_3;
     if(x.getEntityLocation() == null)
        foo_3 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_3 = new edu.nps.moves.dis.Vector3Double(x.getEntityLocation() );
     this.setEntityLocation(foo_3);


     edu.nps.moves.dis.Orientation foo_4;
     if(x.getEntityOrientation() == null)
        foo_4 = new edu.nps.moves.dis.Orientation();
      else
        foo_4 = new edu.nps.moves.dis.Orientation(x.getEntityOrientation() );
     this.setEntityOrientation(foo_4);

     this.entityAppearance = x.getEntityAppearance();
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
 public edu.nps.moves.jaxb.dis.EntityStateUpdatePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.EntityStateUpdatePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setEntityID( this.getEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setNumberOfArticulationParameters( this.getNumberOfArticulationParameters() );
     x.setEntityLinearVelocity( this.getEntityLinearVelocity().initializeJaxbObject(factory.createVector3Float()) );
     x.setEntityLocation( this.getEntityLocation().initializeJaxbObject(factory.createVector3Double()) );
     x.setEntityOrientation( this.getEntityOrientation().initializeJaxbObject(factory.createOrientation()) );
     x.setEntityAppearance( this.getEntityAppearance() );

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
   marshalSize = marshalSize + 1;  // numberOfArticulationParameters
   marshalSize = marshalSize + entityLinearVelocity.getMarshalledSize();  // entityLinearVelocity
   marshalSize = marshalSize + entityLocation.getMarshalledSize();  // entityLocation
   marshalSize = marshalSize + entityOrientation.getMarshalledSize();  // entityOrientation
   marshalSize = marshalSize + 4;  // entityAppearance
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
       dos.writeByte( (byte)articulationParameters.size());
       entityLinearVelocity.marshal(dos);
       entityLocation.marshal(dos);
       entityOrientation.marshal(dos);
       dos.writeInt( (int)entityAppearance);

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
       numberOfArticulationParameters = dis.readByte();
       entityLinearVelocity.unmarshal(dis);
       entityLocation.unmarshal(dis);
       entityOrientation.unmarshal(dis);
       entityAppearance = dis.readInt();
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
 public boolean equals(EntityStateUpdatePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (entityID.equals( rhs.entityID) )) ivarsEqual = false;
     if( ! (numberOfArticulationParameters == rhs.numberOfArticulationParameters)) ivarsEqual = false;
     if( ! (entityLinearVelocity.equals( rhs.entityLinearVelocity) )) ivarsEqual = false;
     if( ! (entityLocation.equals( rhs.entityLocation) )) ivarsEqual = false;
     if( ! (entityOrientation.equals( rhs.entityOrientation) )) ivarsEqual = false;
     if( ! (entityAppearance == rhs.entityAppearance)) ivarsEqual = false;

     for(int idx = 0; idx < articulationParameters.size(); idx++)
     {
        ArticulationParameter x = (ArticulationParameter)articulationParameters.get(idx);
        if( ! ( articulationParameters.get(idx).equals(rhs.articulationParameters.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
