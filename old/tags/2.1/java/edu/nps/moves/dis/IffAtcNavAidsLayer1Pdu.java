package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.3.7.4.1: Navigational and IFF PDU. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IffAtcNavAidsLayer1Pdu extends DistributedEmissionsFamilyPdu implements Serializable
{
   /** ID of the entity that is the source of the emissions */
   protected EntityID  emittingEntityId = new EntityID(); 

   /** Number generated by the issuing simulation to associate realted events. */
   protected EventID  eventID = new EventID(); 

   /** Location wrt entity. There is some ambugiuity in the standard here, but this is the order it is listed in the table. */
   protected Vector3Float  location = new Vector3Float(); 

   /** System ID information */
   protected SystemID  systemID = new SystemID(); 

   /** padding */
   protected int  pad2;

   /** fundamental parameters */
   protected IffFundamentalData  fundamentalParameters = new IffFundamentalData(); 


/** Constructor */
 public IffAtcNavAidsLayer1Pdu()
 {
    setPduType( (short)28 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public IffAtcNavAidsLayer1Pdu(edu.nps.moves.jaxb.dis.IffAtcNavAidsLayer1Pdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getEmittingEntityId() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getEmittingEntityId() );
     this.setEmittingEntityId(foo_0);


     edu.nps.moves.dis.EventID foo_1;
     if(x.getEventID() == null)
        foo_1 = new edu.nps.moves.dis.EventID();
      else
        foo_1 = new edu.nps.moves.dis.EventID(x.getEventID() );
     this.setEventID(foo_1);


     edu.nps.moves.dis.Vector3Float foo_2;
     if(x.getLocation() == null)
        foo_2 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_2 = new edu.nps.moves.dis.Vector3Float(x.getLocation() );
     this.setLocation(foo_2);


     edu.nps.moves.dis.SystemID foo_3;
     if(x.getSystemID() == null)
        foo_3 = new edu.nps.moves.dis.SystemID();
      else
        foo_3 = new edu.nps.moves.dis.SystemID(x.getSystemID() );
     this.setSystemID(foo_3);

     this.pad2 = x.getPad2();

     edu.nps.moves.dis.IffFundamentalData foo_5;
     if(x.getFundamentalParameters() == null)
        foo_5 = new edu.nps.moves.dis.IffFundamentalData();
      else
        foo_5 = new edu.nps.moves.dis.IffFundamentalData(x.getFundamentalParameters() );
     this.setFundamentalParameters(foo_5);

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.IffAtcNavAidsLayer1Pdu initializeJaxbObject(edu.nps.moves.jaxb.dis.IffAtcNavAidsLayer1Pdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setEmittingEntityId( this.getEmittingEntityId().initializeJaxbObject(factory.createEntityID()) );
     x.setEventID( this.getEventID().initializeJaxbObject(factory.createEventID()) );
     x.setLocation( this.getLocation().initializeJaxbObject(factory.createVector3Float()) );
     x.setSystemID( this.getSystemID().initializeJaxbObject(factory.createSystemID()) );
     x.setPad2( this.getPad2() );
     x.setFundamentalParameters( this.getFundamentalParameters().initializeJaxbObject(factory.createIffFundamentalData()) );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + emittingEntityId.getMarshalledSize();  // emittingEntityId
   marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
   marshalSize = marshalSize + location.getMarshalledSize();  // location
   marshalSize = marshalSize + systemID.getMarshalledSize();  // systemID
   marshalSize = marshalSize + 2;  // pad2
   marshalSize = marshalSize + fundamentalParameters.getMarshalledSize();  // fundamentalParameters

   return marshalSize;
}


public void setEmittingEntityId(EntityID pEmittingEntityId)
{ emittingEntityId = pEmittingEntityId;
}

public EntityID getEmittingEntityId()
{ return emittingEntityId; }

public void setEventID(EventID pEventID)
{ eventID = pEventID;
}

public EventID getEventID()
{ return eventID; }

public void setLocation(Vector3Float pLocation)
{ location = pLocation;
}

public Vector3Float getLocation()
{ return location; }

public void setSystemID(SystemID pSystemID)
{ systemID = pSystemID;
}

public SystemID getSystemID()
{ return systemID; }

public void setPad2(int pPad2)
{ pad2 = pPad2;
}

public int getPad2()
{ return pad2; 
}

public void setFundamentalParameters(IffFundamentalData pFundamentalParameters)
{ fundamentalParameters = pFundamentalParameters;
}

public IffFundamentalData getFundamentalParameters()
{ return fundamentalParameters; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       emittingEntityId.marshal(dos);
       eventID.marshal(dos);
       location.marshal(dos);
       systemID.marshal(dos);
       dos.writeShort( (short)pad2);
       fundamentalParameters.marshal(dos);
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
       emittingEntityId.unmarshal(dis);
       eventID.unmarshal(dis);
       location.unmarshal(dis);
       systemID.unmarshal(dis);
       pad2 = dis.readShort();
       fundamentalParameters.unmarshal(dis);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(IffAtcNavAidsLayer1Pdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (emittingEntityId.equals( rhs.emittingEntityId) )) ivarsEqual = false;
     if( ! (eventID.equals( rhs.eventID) )) ivarsEqual = false;
     if( ! (location.equals( rhs.location) )) ivarsEqual = false;
     if( ! (systemID.equals( rhs.systemID) )) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;
     if( ! (fundamentalParameters.equals( rhs.fundamentalParameters) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
