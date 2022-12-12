package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.4.2. Information about stuff exploding. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class DetonationPdu extends WarfareFamilyPdu implements Serializable
{
   /** ID of muntion that was fired */
   protected EntityID  munitionID = new EntityID(); 

   /** ID firing event */
   protected EventID  eventID = new EventID(); 

   /** ID firing event */
   protected Vector3Float  velocity = new Vector3Float(); 

   /** where the detonation is, in world coordinates */
   protected Vector3Double  locationInWorldCoordinates = new Vector3Double(); 

   /** Describes munition used */
   protected BurstDescriptor  burstDescriptor = new BurstDescriptor(); 

   /** result of the explosion */
   protected short  detonationResult;

   /** How many articulation parameters we have */
   protected short  numberOfArticulationParameters;

   /** padding */
   protected short  pad = 0;

   protected List articulationParameters = new ArrayList(); 

/** Constructor */
 public DetonationPdu()
 {
    setPduType( (short)3 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public DetonationPdu(edu.nps.moves.jaxb.dis.DetonationPdu x)
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


     edu.nps.moves.dis.Vector3Float foo_2;
     if(x.getVelocity() == null)
        foo_2 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_2 = new edu.nps.moves.dis.Vector3Float(x.getVelocity() );
     this.setVelocity(foo_2);


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

     this.detonationResult = x.getDetonationResult();
     this.numberOfArticulationParameters = x.getNumberOfArticulationParameters();
     this.pad = x.getPad();
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
 public edu.nps.moves.jaxb.dis.DetonationPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.DetonationPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setMunitionID( this.getMunitionID().initializeJaxbObject(factory.createEntityID()) );
     x.setEventID( this.getEventID().initializeJaxbObject(factory.createEventID()) );
     x.setVelocity( this.getVelocity().initializeJaxbObject(factory.createVector3Float()) );
     x.setLocationInWorldCoordinates( this.getLocationInWorldCoordinates().initializeJaxbObject(factory.createVector3Double()) );
     x.setBurstDescriptor( this.getBurstDescriptor().initializeJaxbObject(factory.createBurstDescriptor()) );
     x.setDetonationResult( this.getDetonationResult() );
     x.setNumberOfArticulationParameters( this.getNumberOfArticulationParameters() );
     x.setPad( this.getPad() );

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
   marshalSize = marshalSize + munitionID.getMarshalledSize();  // munitionID
   marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
   marshalSize = marshalSize + velocity.getMarshalledSize();  // velocity
   marshalSize = marshalSize + locationInWorldCoordinates.getMarshalledSize();  // locationInWorldCoordinates
   marshalSize = marshalSize + burstDescriptor.getMarshalledSize();  // burstDescriptor
   marshalSize = marshalSize + 1;  // detonationResult
   marshalSize = marshalSize + 1;  // numberOfArticulationParameters
   marshalSize = marshalSize + 2;  // pad
   for(int idx=0; idx < articulationParameters.size(); idx++)
   {
        ArticulationParameter listElement = (ArticulationParameter)articulationParameters.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

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

public void setVelocity(Vector3Float pVelocity)
{ velocity = pVelocity;
}

public Vector3Float getVelocity()
{ return velocity; }

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

public void setDetonationResult(short pDetonationResult)
{ detonationResult = pDetonationResult;
}

public short getDetonationResult()
{ return detonationResult; 
}

public short getNumberOfArticulationParameters()
{ return (short)articulationParameters.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfArticulationParameters method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfArticulationParameters(short pNumberOfArticulationParameters)
{ numberOfArticulationParameters = pNumberOfArticulationParameters;
}

public void setPad(short pPad)
{ pad = pPad;
}

public short getPad()
{ return pad; 
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
       munitionID.marshal(dos);
       eventID.marshal(dos);
       velocity.marshal(dos);
       locationInWorldCoordinates.marshal(dos);
       burstDescriptor.marshal(dos);
       dos.writeByte( (byte)detonationResult);
       dos.writeByte( (byte)articulationParameters.size());
       dos.writeShort( (short)pad);

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
       munitionID.unmarshal(dis);
       eventID.unmarshal(dis);
       velocity.unmarshal(dis);
       locationInWorldCoordinates.unmarshal(dis);
       burstDescriptor.unmarshal(dis);
       detonationResult = dis.readByte();
       numberOfArticulationParameters = dis.readByte();
       pad = dis.readShort();
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
 public boolean equals(DetonationPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (munitionID.equals( rhs.munitionID) )) ivarsEqual = false;
     if( ! (eventID.equals( rhs.eventID) )) ivarsEqual = false;
     if( ! (velocity.equals( rhs.velocity) )) ivarsEqual = false;
     if( ! (locationInWorldCoordinates.equals( rhs.locationInWorldCoordinates) )) ivarsEqual = false;
     if( ! (burstDescriptor.equals( rhs.burstDescriptor) )) ivarsEqual = false;
     if( ! (detonationResult == rhs.detonationResult)) ivarsEqual = false;
     if( ! (numberOfArticulationParameters == rhs.numberOfArticulationParameters)) ivarsEqual = false;
     if( ! (pad == rhs.pad)) ivarsEqual = false;

     for(int idx = 0; idx < articulationParameters.size(); idx++)
     {
        ArticulationParameter x = (ArticulationParameter)articulationParameters.get(idx);
        if( ! ( articulationParameters.get(idx).equals(rhs.articulationParameters.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
