package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.7.1. Information about active electronic warfare (EW) emissions and active EW countermeasures shall be communicated using an Electromagnetic Emission PDU. COMPLETE (I think)
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ElectronicEmissionsPdu extends DistributedEmissionsFamilyPdu implements Serializable
{
   /** ID of the entity emitting */
   protected EntityID  emittingEntityID = new EntityID(); 

   /** ID of event */
   protected EventID  eventID = new EventID(); 

   /** This field shall be used to indicate if the data in the PDU represents a state update or just data that has changed since issuance of the last Electromagnetic Emission PDU [relative to the identified entity and emission system(s)]. */
   protected short  stateUpdateIndicator;

   /** This field shall specify the number of emission systems being described in the current PDU. */
   protected short  numberOfSystems;

   /** Electronic emmissions systems */
   protected List systems = new ArrayList(); 

/** Constructor */
 public ElectronicEmissionsPdu()
 {
    setPduType( (short)23 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ElectronicEmissionsPdu(edu.nps.moves.jaxb.dis.ElectronicEmissionsPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getEmittingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getEmittingEntityID() );
     this.setEmittingEntityID(foo_0);


     edu.nps.moves.dis.EventID foo_1;
     if(x.getEventID() == null)
        foo_1 = new edu.nps.moves.dis.EventID();
      else
        foo_1 = new edu.nps.moves.dis.EventID(x.getEventID() );
     this.setEventID(foo_1);

     this.stateUpdateIndicator = x.getStateUpdateIndicator();
     this.numberOfSystems = x.getNumberOfSystems();
     this.systems = new ArrayList();
     for(int idx = 0; idx < x.getSystems().size(); idx++)
     {
        this.systems.add( new edu.nps.moves.dis.ElectronicEmissionSystemData((edu.nps.moves.jaxb.dis.ElectronicEmissionSystemData) x.getSystems().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ElectronicEmissionsPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.ElectronicEmissionsPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setEmittingEntityID( this.getEmittingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setEventID( this.getEventID().initializeJaxbObject(factory.createEventID()) );
     x.setStateUpdateIndicator( this.getStateUpdateIndicator() );
     x.setNumberOfSystems( this.getNumberOfSystems() );

     List systems_1 = x.getSystems();
     for(int idx = 0; idx < systems.size(); idx++)
     {
         ElectronicEmissionSystemData a = (edu.nps.moves.dis.ElectronicEmissionSystemData)systems.get(idx);
         systems_1.add(a.initializeJaxbObject(factory.createElectronicEmissionSystemData()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + emittingEntityID.getMarshalledSize();  // emittingEntityID
   marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
   marshalSize = marshalSize + 1;  // stateUpdateIndicator
   marshalSize = marshalSize + 1;  // numberOfSystems
   for(int idx=0; idx < systems.size(); idx++)
   {
        ElectronicEmissionSystemData listElement = (ElectronicEmissionSystemData)systems.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setEmittingEntityID(EntityID pEmittingEntityID)
{ emittingEntityID = pEmittingEntityID;
}

public EntityID getEmittingEntityID()
{ return emittingEntityID; }

public void setEventID(EventID pEventID)
{ eventID = pEventID;
}

public EventID getEventID()
{ return eventID; }

public void setStateUpdateIndicator(short pStateUpdateIndicator)
{ stateUpdateIndicator = pStateUpdateIndicator;
}

public short getStateUpdateIndicator()
{ return stateUpdateIndicator; 
}

public short getNumberOfSystems()
{ return (short)systems.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfSystems method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfSystems(short pNumberOfSystems)
{ numberOfSystems = pNumberOfSystems;
}

public void setSystems(List pSystems)
{ systems = pSystems;
}

public List getSystems()
{ return systems; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       emittingEntityID.marshal(dos);
       eventID.marshal(dos);
       dos.writeByte( (byte)stateUpdateIndicator);
       dos.writeByte( (byte)systems.size());

       for(int idx = 0; idx < systems.size(); idx++)
       {
            ElectronicEmissionSystemData aElectronicEmissionSystemData = (ElectronicEmissionSystemData)systems.get(idx);
            aElectronicEmissionSystemData.marshal(dos);
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
       emittingEntityID.unmarshal(dis);
       eventID.unmarshal(dis);
       stateUpdateIndicator = dis.readByte();
       numberOfSystems = dis.readByte();
        for(int idx = 0; idx < numberOfSystems; idx++)
        {
           ElectronicEmissionSystemData anX = new ElectronicEmissionSystemData();
            anX.unmarshal(dis);
            systems.add(anX);
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
 public boolean equals(ElectronicEmissionsPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (emittingEntityID.equals( rhs.emittingEntityID) )) ivarsEqual = false;
     if( ! (eventID.equals( rhs.eventID) )) ivarsEqual = false;
     if( ! (stateUpdateIndicator == rhs.stateUpdateIndicator)) ivarsEqual = false;
     if( ! (numberOfSystems == rhs.numberOfSystems)) ivarsEqual = false;

     for(int idx = 0; idx < systems.size(); idx++)
     {
        ElectronicEmissionSystemData x = (ElectronicEmissionSystemData)systems.get(idx);
        if( ! ( systems.get(idx).equals(rhs.systems.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
