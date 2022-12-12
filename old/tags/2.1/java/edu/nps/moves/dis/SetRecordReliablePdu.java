package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.12.14: Initializing or changing internal parameter info. Needs manual intervention     to fix padding in recrod set PDUs. UNFINISHED
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SetRecordReliablePdu extends SimulationManagementWithReliabilityFamilyPdu implements Serializable
{
   /** request ID */
   protected long  requestID;

   /** level of reliability service used for this transaction */
   protected short  requiredReliabilityService;

   /** padding. The spec is unclear and contradictory here. */
   protected int  pad1;

   /** padding */
   protected short  pad2;

   /** Number of record sets in list */
   protected long  numberOfRecordSets;

   /** record sets */
   protected List recordSets = new ArrayList(); 

/** Constructor */
 public SetRecordReliablePdu()
 {
    setPduType( (short)64 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SetRecordReliablePdu(edu.nps.moves.jaxb.dis.SetRecordReliablePdu x)
 {
     super(x); // Call superclass constructor

     this.requestID = x.getRequestID();
     this.requiredReliabilityService = x.getRequiredReliabilityService();
     this.pad1 = x.getPad1();
     this.pad2 = x.getPad2();
     this.numberOfRecordSets = x.getNumberOfRecordSets();
     this.recordSets = new ArrayList();
     for(int idx = 0; idx < x.getRecordSets().size(); idx++)
     {
        this.recordSets.add( new edu.nps.moves.dis.RecordSet((edu.nps.moves.jaxb.dis.RecordSet) x.getRecordSets().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SetRecordReliablePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.SetRecordReliablePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRequestID( this.getRequestID() );
     x.setRequiredReliabilityService( this.getRequiredReliabilityService() );
     x.setPad1( this.getPad1() );
     x.setPad2( this.getPad2() );
     x.setNumberOfRecordSets( this.getNumberOfRecordSets() );

     List recordSets_1 = x.getRecordSets();
     for(int idx = 0; idx < recordSets.size(); idx++)
     {
         RecordSet a = (edu.nps.moves.dis.RecordSet)recordSets.get(idx);
         recordSets_1.add(a.initializeJaxbObject(factory.createRecordSet()));
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
   marshalSize = marshalSize + 4;  // numberOfRecordSets
   for(int idx=0; idx < recordSets.size(); idx++)
   {
        RecordSet listElement = (RecordSet)recordSets.get(idx);
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

public long getNumberOfRecordSets()
{ return (long)recordSets.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfRecordSets method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfRecordSets(long pNumberOfRecordSets)
{ numberOfRecordSets = pNumberOfRecordSets;
}

public void setRecordSets(List pRecordSets)
{ recordSets = pRecordSets;
}

public List getRecordSets()
{ return recordSets; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeInt( (int)requestID);
       dos.writeByte( (byte)requiredReliabilityService);
       dos.writeShort( (short)pad1);
       dos.writeByte( (byte)pad2);
       dos.writeInt( (int)recordSets.size());

       for(int idx = 0; idx < recordSets.size(); idx++)
       {
            RecordSet aRecordSet = (RecordSet)recordSets.get(idx);
            aRecordSet.marshal(dos);
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
       numberOfRecordSets = dis.readInt();
        for(int idx = 0; idx < numberOfRecordSets; idx++)
        {
           RecordSet anX = new RecordSet();
            anX.unmarshal(dis);
            recordSets.add(anX);
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
 public boolean equals(SetRecordReliablePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (requestID == rhs.requestID)) ivarsEqual = false;
     if( ! (requiredReliabilityService == rhs.requiredReliabilityService)) ivarsEqual = false;
     if( ! (pad1 == rhs.pad1)) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;
     if( ! (numberOfRecordSets == rhs.numberOfRecordSets)) ivarsEqual = false;

     for(int idx = 0; idx < recordSets.size(); idx++)
     {
        RecordSet x = (RecordSet)recordSets.get(idx);
        if( ! ( recordSets.get(idx).equals(rhs.recordSets.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
