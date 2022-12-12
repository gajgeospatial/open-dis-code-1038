package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.12.13: A request for one or more records of data from an entity. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class RecordQueryReliablePdu extends SimulationManagementWithReliabilityFamilyPdu implements Serializable
{
   /** request ID */
   protected long  requestID;

   /** level of reliability service used for this transaction */
   protected short  requiredReliabilityService;

   /** padding. The spec is unclear and contradictory here. */
   protected int  pad1;

   /** padding */
   protected short  pad2;

   /** event type */
   protected int  eventType;

   /** time */
   protected long  time;

   /** numberOfRecords */
   protected long  numberOfRecords;

   /** record IDs */
   protected List recordIDs = new ArrayList(); 

/** Constructor */
 public RecordQueryReliablePdu()
 {
    setPduType( (short)63 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public RecordQueryReliablePdu(edu.nps.moves.jaxb.dis.RecordQueryReliablePdu x)
 {
     super(x); // Call superclass constructor

     this.requestID = x.getRequestID();
     this.requiredReliabilityService = x.getRequiredReliabilityService();
     this.pad1 = x.getPad1();
     this.pad2 = x.getPad2();
     this.eventType = x.getEventType();
     this.time = x.getTime();
     this.numberOfRecords = x.getNumberOfRecords();
     this.recordIDs = new ArrayList();
     for(int idx = 0; idx < x.getRecordIDs().size(); idx++)
     {
        this.recordIDs.add( new edu.nps.moves.dis.FourByteChunk((edu.nps.moves.jaxb.dis.FourByteChunk) x.getRecordIDs().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.RecordQueryReliablePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.RecordQueryReliablePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRequestID( this.getRequestID() );
     x.setRequiredReliabilityService( this.getRequiredReliabilityService() );
     x.setPad1( this.getPad1() );
     x.setPad2( this.getPad2() );
     x.setEventType( this.getEventType() );
     x.setTime( this.getTime() );
     x.setNumberOfRecords( this.getNumberOfRecords() );

     List recordIDs_1 = x.getRecordIDs();
     for(int idx = 0; idx < recordIDs.size(); idx++)
     {
         FourByteChunk a = (edu.nps.moves.dis.FourByteChunk)recordIDs.get(idx);
         recordIDs_1.add(a.initializeJaxbObject(factory.createFourByteChunk()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 4;  // requestID
   marshalSize = marshalSize + 1;  // requiredReliabilityService
   marshalSize = marshalSize + 2;  // pad1
   marshalSize = marshalSize + 1;  // pad2
   marshalSize = marshalSize + 2;  // eventType
   marshalSize = marshalSize + 4;  // time
   marshalSize = marshalSize + 4;  // numberOfRecords
   for(int idx=0; idx < recordIDs.size(); idx++)
   {
        FourByteChunk listElement = (FourByteChunk)recordIDs.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setRequestID(long pRequestID)
{ requestID = pRequestID;
}

public long getRequestID()
{ return requestID; 
}

public void setRequiredReliabilityService(short pRequiredReliabilityService)
{ requiredReliabilityService = pRequiredReliabilityService;
}

public short getRequiredReliabilityService()
{ return requiredReliabilityService; 
}

public void setPad1(int pPad1)
{ pad1 = pPad1;
}

public int getPad1()
{ return pad1; 
}

public void setPad2(short pPad2)
{ pad2 = pPad2;
}

public short getPad2()
{ return pad2; 
}

public void setEventType(int pEventType)
{ eventType = pEventType;
}

public int getEventType()
{ return eventType; 
}

public void setTime(long pTime)
{ time = pTime;
}

public long getTime()
{ return time; 
}

public long getNumberOfRecords()
{ return (long)recordIDs.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfRecords(long pNumberOfRecords)
{ numberOfRecords = pNumberOfRecords;
}

public void setRecordIDs(List pRecordIDs)
{ recordIDs = pRecordIDs;
}

public List getRecordIDs()
{ return recordIDs; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeInt( (int)requestID);
       dos.writeByte( (byte)requiredReliabilityService);
       dos.writeShort( (short)pad1);
       dos.writeByte( (byte)pad2);
       dos.writeShort( (short)eventType);
       dos.writeInt( (int)time);
       dos.writeInt( (int)recordIDs.size());

       for(int idx = 0; idx < recordIDs.size(); idx++)
       {
            FourByteChunk aFourByteChunk = (FourByteChunk)recordIDs.get(idx);
            aFourByteChunk.marshal(dos);
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
       requestID = dis.readInt();
       requiredReliabilityService = dis.readByte();
       pad1 = dis.readShort();
       pad2 = dis.readByte();
       eventType = dis.readShort();
       time = dis.readInt();
       numberOfRecords = dis.readInt();
        for(int idx = 0; idx < numberOfRecords; idx++)
        {
           FourByteChunk anX = new FourByteChunk();
            anX.unmarshal(dis);
            recordIDs.add(anX);
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
 public boolean equals(RecordQueryReliablePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (requestID == rhs.requestID)) ivarsEqual = false;
     if( ! (requiredReliabilityService == rhs.requiredReliabilityService)) ivarsEqual = false;
     if( ! (pad1 == rhs.pad1)) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;
     if( ! (eventType == rhs.eventType)) ivarsEqual = false;
     if( ! (time == rhs.time)) ivarsEqual = false;
     if( ! (numberOfRecords == rhs.numberOfRecords)) ivarsEqual = false;

     for(int idx = 0; idx < recordIDs.size(); idx++)
     {
        FourByteChunk x = (FourByteChunk)recordIDs.get(idx);
        if( ! ( recordIDs.get(idx).equals(rhs.recordIDs.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
