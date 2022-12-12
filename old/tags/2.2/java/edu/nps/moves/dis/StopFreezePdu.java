package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.3.4. Stop or freeze an exercise. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class StopFreezePdu extends SimulationManagementFamilyPdu implements Serializable
{
   /** UTC time at which the simulation shall stop or freeze */
   protected ClockTime  realWorldTime = new ClockTime(); 

   /** Reason the simulation was stopped or frozen */
   protected short  reason;

   /** Internal behavior of the simulation and its appearance while frozento the other participants */
   protected short  frozenBehavior;

   /** padding */
   protected short  padding1 = 0;

   /** Request ID that is unique */
   protected long  requestID;


/** Constructor */
 public StopFreezePdu()
 {
    setPduType( (short)14 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public StopFreezePdu(edu.nps.moves.jaxb.dis.StopFreezePdu x)
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
     this.padding1 = x.getPadding1();
     this.requestID = x.getRequestID();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.StopFreezePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.StopFreezePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRealWorldTime( this.getRealWorldTime().initializeJaxbObject(factory.createClockTime()) );
     x.setReason( this.getReason() );
     x.setFrozenBehavior( this.getFrozenBehavior() );
     x.setPadding1( this.getPadding1() );
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
   marshalSize = marshalSize + 2;  // padding1
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

public void setPadding1(short pPadding1)
{ padding1 = pPadding1;
}

public short getPadding1()
{ return padding1; 
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
       dos.writeShort( (short)padding1);
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
       padding1 = dis.readShort();
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
 public boolean equals(StopFreezePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (realWorldTime.equals( rhs.realWorldTime) )) ivarsEqual = false;
     if( ! (reason == rhs.reason)) ivarsEqual = false;
     if( ! (frozenBehavior == rhs.frozenBehavior)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (requestID == rhs.requestID)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
