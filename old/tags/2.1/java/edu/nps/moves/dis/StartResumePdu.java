package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.6.3. Start or resume an exercise. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class StartResumePdu extends SimulationManagementFamilyPdu implements Serializable
{
   /** UTC time at which the simulation shall start or resume */
   protected ClockTime  realWorldTime = new ClockTime(); 

   /** Simulation clock time at which the simulation shall start or resume */
   protected ClockTime  simulationTime = new ClockTime(); 

   /** Identifier for the request */
   protected long  requestID;


/** Constructor */
 public StartResumePdu()
 {
    setPduType( (short)13 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public StartResumePdu(edu.nps.moves.jaxb.dis.StartResumePdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.ClockTime foo_0;
     if(x.getRealWorldTime() == null)
        foo_0 = new edu.nps.moves.dis.ClockTime();
      else
        foo_0 = new edu.nps.moves.dis.ClockTime(x.getRealWorldTime() );
     this.setRealWorldTime(foo_0);


     edu.nps.moves.dis.ClockTime foo_1;
     if(x.getSimulationTime() == null)
        foo_1 = new edu.nps.moves.dis.ClockTime();
      else
        foo_1 = new edu.nps.moves.dis.ClockTime(x.getSimulationTime() );
     this.setSimulationTime(foo_1);

     this.requestID = x.getRequestID();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.StartResumePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.StartResumePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRealWorldTime( this.getRealWorldTime().initializeJaxbObject(factory.createClockTime()) );
     x.setSimulationTime( this.getSimulationTime().initializeJaxbObject(factory.createClockTime()) );
     x.setRequestID( this.getRequestID() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + realWorldTime.getMarshalledSize();  // realWorldTime
   marshalSize = marshalSize + simulationTime.getMarshalledSize();  // simulationTime
   marshalSize = marshalSize + 4;  // requestID

   return marshalSize;
}


public void setRealWorldTime(ClockTime pRealWorldTime)
{ realWorldTime = pRealWorldTime;
}

public ClockTime getRealWorldTime()
{ return realWorldTime; }

public void setSimulationTime(ClockTime pSimulationTime)
{ simulationTime = pSimulationTime;
}

public ClockTime getSimulationTime()
{ return simulationTime; }

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
       simulationTime.marshal(dos);
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
       simulationTime.unmarshal(dis);
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
 public boolean equals(StartResumePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (realWorldTime.equals( rhs.realWorldTime) )) ivarsEqual = false;
     if( ! (simulationTime.equals( rhs.simulationTime) )) ivarsEqual = false;
     if( ! (requestID == rhs.requestID)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
