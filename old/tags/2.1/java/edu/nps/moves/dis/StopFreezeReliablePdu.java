package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.12.4: Stop freeze simulation, relaible. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class StopFreezeReliablePdu extends SimulationManagementWithReliabilityFamilyPdu implements Serializable
{
   /** time in real world for this operation to happen */
   protected ClockTime  realWorldTime = new ClockTime(); 

   /** Reason for stopping/freezing simulation */
   protected short  reason;

   /** internal behvior of the simulation while frozen */
   protected short  frozenBehavior;

   /** reliablity level */
   protected short  requiredReliablityService;

   /** padding */
   protected short  pad1;

   /** Request ID */
   protected long  requestID;


/** Constructor */
 public StopFreezeReliablePdu()
 {
    setPduType( (short)54 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public StopFreezeReliablePdu(edu.nps.moves.jaxb.dis.StopFreezeReliablePdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.ClockTime foo_0;
     if(x.getRealWorldTime() == null)
        foo_0 = new edu.nps.moves.dis.ClockTime();
      else
        foo_0 = new edu.nps.moves.dis.ClockTime(x.getRealWorldTime() );
     this.setRealWorldTime(foo_0);

     this.reason = x.getReason();
     this.frozenBehavior = x.getFrozenBehavior();
     this.requiredReliablityService = x.getRequiredReliablityService();
     this.pad1 = x.getPad1();
     this.requestID = x.getRequestID();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.StopFreezeReliablePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.StopFreezeReliablePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRealWorldTime( this.getRealWorldTime().initializeJaxbObject(factory.createClockTime()) );
     x.setReason( this.getReason() );
     x.setFrozenBehavior( this.getFrozenBehavior() );
     x.setRequiredReliablityService( this.getRequiredReliablityService() );
     x.setPad1( this.getPad1() );
     x.setRequestID( this.getRequestID() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + realWorldTime.getMarshalledSize();  // realWorldTime
   marshalSize = marshalSize + 1;  // reason
   marshalSize = marshalSize + 1;  // frozenBehavior
   marshalSize = marshalSize + 1;  // requiredReliablityService
   marshalSize = marshalSize + 1;  // pad1
   marshalSize = marshalSize + 4;  // requestID

   return marshalSize;
}


public void setRealWorldTime(ClockTime pRealWorldTime)
{ realWorldTime = pRealWorldTime;
}

public ClockTime getRealWorldTime()
{ return realWorldTime; }

public void setReason(short pReason)
{ reason = pReason;
}

public short getReason()
{ return reason; 
}

public void setFrozenBehavior(short pFrozenBehavior)
{ frozenBehavior = pFrozenBehavior;
}

public short getFrozenBehavior()
{ return frozenBehavior; 
}

public void setRequiredReliablityService(short pRequiredReliablityService)
{ requiredReliablityService = pRequiredReliablityService;
}

public short getRequiredReliablityService()
{ return requiredReliablityService; 
}

public void setPad1(short pPad1)
{ pad1 = pPad1;
}

public short getPad1()
{ return pad1; 
}

public void setRequestID(long pRequestID)
{ requestID = pRequestID;
}

public long getRequestID()
{ return requestID; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       realWorldTime.marshal(dos);
       dos.writeByte( (byte)reason);
       dos.writeByte( (byte)frozenBehavior);
       dos.writeByte( (byte)requiredReliablityService);
       dos.writeByte( (byte)pad1);
       dos.writeInt( (int)requestID);
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
       realWorldTime.unmarshal(dis);
       reason = dis.readByte();
       frozenBehavior = dis.readByte();
       requiredReliablityService = dis.readByte();
       pad1 = dis.readByte();
       requestID = dis.readInt();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(StopFreezeReliablePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (realWorldTime.equals( rhs.realWorldTime) )) ivarsEqual = false;
     if( ! (reason == rhs.reason)) ivarsEqual = false;
     if( ! (frozenBehavior == rhs.frozenBehavior)) ivarsEqual = false;
     if( ! (requiredReliablityService == rhs.requiredReliablityService)) ivarsEqual = false;
     if( ! (pad1 == rhs.pad1)) ivarsEqual = false;
     if( ! (requestID == rhs.requestID)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
